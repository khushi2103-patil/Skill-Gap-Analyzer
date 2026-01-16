/**
 * Dashboard page functionality
 */

let allSkills = [];
let selectedRole = null;
let selectedSkills = [];

// Initialize dashboard
document.addEventListener('DOMContentLoaded', async () => {
    requireAuth();
    
    // Load user info
    const user = getUser();
    if (user) {
        document.getElementById('userName').textContent = user.name;
    }
    
    // Setup logout
    document.getElementById('logoutLink').addEventListener('click', (e) => {
        e.preventDefault();
        logout();
    });
    
    // Load career roles and skills
    await loadCareerRoles();
    await loadAllSkills();
    
    // Setup event listeners
    document.getElementById('careerRoleSelect').addEventListener('change', handleRoleChange);
    document.getElementById('analyzeBtn').addEventListener('click', analyzeSkillGap);
    document.getElementById('roadmapBtn').addEventListener('click', loadRoadmap);
});

/**
 * Load all career roles
 */
async function loadCareerRoles() {
    try {
        const response = await apiRequest('/roles');
        const roles = await response.json();
        
        const select = document.getElementById('careerRoleSelect');
        roles.forEach(role => {
            const option = document.createElement('option');
            option.value = role.id;
            option.textContent = role.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading roles:', error);
        alert('Failed to load career roles. Please refresh the page.');
    }
}

/**
 * Load all available skills
 */
async function loadAllSkills() {
    try {
        const response = await apiRequest('/skills');
        allSkills = await response.json();
    } catch (error) {
        console.error('Error loading skills:', error);
    }
}

/**
 * Handle career role selection change
 */
async function handleRoleChange() {
    const roleId = document.getElementById('careerRoleSelect').value;
    
    if (!roleId) {
        document.getElementById('skillsContainer').innerHTML = '<p class="text-muted">Select a career role first</p>';
        document.getElementById('roleDescription').textContent = '';
        document.getElementById('analyzeBtn').disabled = true;
        selectedRole = null;
        return;
    }
    
    try {
        const response = await apiRequest(`/roles/${roleId}`);
        selectedRole = await response.json();
        
        // Display role description
        document.getElementById('roleDescription').textContent = 
            selectedRole.description || 'No description available';
        
        // Load required skills for this role
        displaySkillsForRole(selectedRole);
        
        document.getElementById('analyzeBtn').disabled = false;
    } catch (error) {
        console.error('Error loading role:', error);
        alert('Failed to load role details.');
    }
}

/**
 * Display skills for selected role
 */
function displaySkillsForRole(role) {
    const container = document.getElementById('skillsContainer');
    container.innerHTML = '';
    
    if (!role.requiredSkills || role.requiredSkills.length === 0) {
        container.innerHTML = '<p class="text-muted">No skills defined for this role.</p>';
        return;
    }
    
    role.requiredSkills.forEach(skill => {
        const div = document.createElement('div');
        div.className = 'form-check';
        
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'form-check-input skill-checkbox';
        checkbox.value = skill.id;
        checkbox.id = `skill-${skill.id}`;
        checkbox.addEventListener('change', handleSkillChange);
        
        const label = document.createElement('label');
        label.className = 'form-check-label';
        label.htmlFor = `skill-${skill.id}`;
        label.textContent = skill.name;
        
        div.appendChild(checkbox);
        div.appendChild(label);
        container.appendChild(div);
    });
    
    selectedSkills = [];
}

/**
 * Handle skill checkbox change
 */
function handleSkillChange(e) {
    const skillId = parseInt(e.target.value);
    
    if (e.target.checked) {
        if (!selectedSkills.includes(skillId)) {
            selectedSkills.push(skillId);
        }
    } else {
        selectedSkills = selectedSkills.filter(id => id !== skillId);
    }
}

/**
 * Analyze skill gap
 */
async function analyzeSkillGap() {
    if (!selectedRole) {
        alert('Please select a career role first.');
        return;
    }
    
    try {
        const response = await apiRequest('/skills/analyze', {
            method: 'POST',
            body: JSON.stringify({
                roleId: selectedRole.id,
                userSkillIds: selectedSkills
            })
        });
        
        if (response.ok) {
            const result = await response.json();
            displayAnalysisResult(result);
        } else {
            alert('Failed to analyze skills. Please try again.');
        }
    } catch (error) {
        console.error('Error analyzing skills:', error);
        alert('An error occurred. Please try again.');
    }
}

/**
 * Display analysis results
 */
function displayAnalysisResult(result) {
    document.getElementById('analysisResult').style.display = 'block';
    document.getElementById('roadmapResult').style.display = 'none';
    
    document.getElementById('roleNameResult').textContent = `Role: ${result.roleName}`;
    document.getElementById('matchedCount').textContent = result.matchedCount;
    document.getElementById('totalCount').textContent = result.totalRequiredSkills;
    
    const percentage = Math.round(result.matchPercentage);
    document.getElementById('matchPercentage').textContent = `${percentage}%`;
    
    const progressBar = document.getElementById('matchProgressBar');
    progressBar.style.width = `${percentage}%`;
    progressBar.className = `progress-bar ${percentage >= 70 ? 'bg-success' : percentage >= 40 ? 'bg-warning' : 'bg-danger'}`;
    
    // Display matched skills
    const matchedList = document.getElementById('matchedSkillsList');
    matchedList.innerHTML = '';
    if (result.matchedSkills && result.matchedSkills.length > 0) {
        result.matchedSkills.forEach(skill => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = skill;
            matchedList.appendChild(li);
        });
    } else {
        const li = document.createElement('li');
        li.className = 'list-group-item text-muted';
        li.textContent = 'No matched skills';
        matchedList.appendChild(li);
    }
    
    // Display missing skills
    const missingList = document.getElementById('missingSkillsList');
    missingList.innerHTML = '';
    if (result.missingSkills && result.missingSkills.length > 0) {
        result.missingSkills.forEach(skill => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = skill;
            missingList.appendChild(li);
        });
    } else {
        const li = document.createElement('li');
        li.className = 'list-group-item text-success';
        li.textContent = 'All skills matched! 🎉';
        missingList.appendChild(li);
    }
}

/**
 * Load learning roadmap
 */
async function loadRoadmap() {
    if (!selectedRole) {
        alert('Please select a career role first.');
        return;
    }
    
    try {
        const response = await apiRequest(`/skills/roadmap/${selectedRole.id}`);
        
        if (response.ok) {
            const roadmap = await response.json();
            displayRoadmap(roadmap);
        } else {
            alert('Failed to load roadmap. Please try again.');
        }
    } catch (error) {
        console.error('Error loading roadmap:', error);
        alert('An error occurred. Please try again.');
    }
}

/**
 * Display learning roadmap
 */
function displayRoadmap(roadmap) {
    document.getElementById('roadmapResult').style.display = 'block';
    
    const container = document.getElementById('roadmapContent');
    container.innerHTML = `<h6 class="mb-3">Learning Roadmap for ${roadmap.roleName}</h6>`;
    
    // Beginner stage
    if (roadmap.beginner && roadmap.beginner.length > 0) {
        const beginnerCard = createStageCard('Beginner', roadmap.beginner, 'stage-beginner');
        container.appendChild(beginnerCard);
    }
    
    // Intermediate stage
    if (roadmap.intermediate && roadmap.intermediate.length > 0) {
        const intermediateCard = createStageCard('Intermediate', roadmap.intermediate, 'stage-intermediate');
        container.appendChild(intermediateCard);
    }
    
    // Advanced stage
    if (roadmap.advanced && roadmap.advanced.length > 0) {
        const advancedCard = createStageCard('Advanced', roadmap.advanced, 'stage-advanced');
        container.appendChild(advancedCard);
    }
}

/**
 * Create a stage card for roadmap
 */
function createStageCard(stageName, items, stageClass) {
    const card = document.createElement('div');
    card.className = 'card mb-3';
    
    const cardHeader = document.createElement('div');
    cardHeader.className = 'card-header';
    cardHeader.innerHTML = `<span class="stage-badge ${stageClass}">${stageName}</span>`;
    
    const cardBody = document.createElement('div');
    cardBody.className = 'card-body';
    
    items.forEach(item => {
        const skillDiv = document.createElement('div');
        skillDiv.className = 'mb-3';
        
        const skillTitle = document.createElement('h6');
        skillTitle.textContent = item.skill;
        skillDiv.appendChild(skillTitle);
        
        if (item.description) {
            const desc = document.createElement('p');
            desc.className = 'text-muted small mb-2';
            desc.textContent = item.description;
            skillDiv.appendChild(desc);
        }
        
        if (item.resources && item.resources.length > 0) {
            const resourcesList = document.createElement('ul');
            resourcesList.className = 'small';
            item.resources.forEach(resource => {
                const li = document.createElement('li');
                li.textContent = resource;
                resourcesList.appendChild(li);
            });
            skillDiv.appendChild(resourcesList);
        }
        
        cardBody.appendChild(skillDiv);
    });
    
    card.appendChild(cardHeader);
    card.appendChild(cardBody);
    
    return card;
}
