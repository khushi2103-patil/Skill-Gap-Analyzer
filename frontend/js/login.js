/**
 * Login page functionality
 */

// Redirect if already authenticated
document.addEventListener('DOMContentLoaded', () => {
    redirectIfAuthenticated();
    
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('errorMessage');
    
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        
        errorMessage.style.display = 'none';
        
        try {
            const response = await apiRequest('/auth/login', {
                method: 'POST',
                body: JSON.stringify({ email, password })
            });
            
            if (response.ok) {
                const data = await response.json();
                saveToken(data.token);
                saveUser({
                    id: data.id,
                    name: data.name,
                    email: data.email,
                    roles: data.roles
                });
                
                // Redirect to dashboard
                window.location.href = 'dashboard.html';
            } else {
                const error = await response.json();
                errorMessage.textContent = error.message || 'Invalid email or password';
                errorMessage.style.display = 'block';
            }
        } catch (error) {
            errorMessage.textContent = error.message || 'An error occurred. Please try again.';
            errorMessage.style.display = 'block';
        }
    });
});
