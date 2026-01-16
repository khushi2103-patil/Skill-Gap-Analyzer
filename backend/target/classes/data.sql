-- Insert Roles
INSERT IGNORE INTO roles (name) VALUES ('ROLE_USER');
INSERT IGNORE INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insert Skills
INSERT IGNORE INTO skills (name, description) VALUES 
('Java', 'Object-oriented programming language'),
('Spring Boot', 'Java framework for building microservices'),
('Spring Security', 'Security framework for Spring applications'),
('MySQL', 'Relational database management system'),
('PostgreSQL', 'Advanced open-source relational database'),
('REST API', 'Architectural style for web services'),
('JWT', 'JSON Web Token for authentication'),
('HTML5', 'Markup language for web pages'),
('CSS3', 'Styling language for web pages'),
('JavaScript', 'Programming language for web development'),
('Bootstrap', 'CSS framework for responsive design'),
('React', 'JavaScript library for building user interfaces'),
('Node.js', 'JavaScript runtime for server-side development'),
('Python', 'High-level programming language'),
('Pandas', 'Python library for data analysis'),
('NumPy', 'Python library for numerical computing'),
('SQL', 'Structured Query Language for databases'),
('Data Visualization', 'Creating visual representations of data'),
('Machine Learning', 'AI technique for pattern recognition'),
('Git', 'Version control system'),
('Maven', 'Build automation tool for Java'),
('Docker', 'Containerization platform'),
('Microservices', 'Architectural pattern for distributed systems'),
('Hibernate', 'Java ORM framework'),
('JPA', 'Java Persistence API');

-- Insert Career Roles
INSERT IGNORE INTO career_roles (name, description) VALUES 
('Frontend Developer', 'Develops user-facing web applications using HTML, CSS, and JavaScript'),
('Java Developer', 'Builds enterprise applications using Java and Spring framework'),
('Data Analyst', 'Analyzes data to extract insights and support decision-making'),
('Backend Developer', 'Develops server-side logic and APIs for web applications');

-- Associate Skills with Frontend Developer
INSERT IGNORE INTO career_role_skills (career_role_id, skill_id)
SELECT cr.id, s.id
FROM career_roles cr, skills s
WHERE cr.name = 'Frontend Developer'
AND s.name IN ('HTML5', 'CSS3', 'JavaScript', 'Bootstrap', 'React', 'Git', 'REST API');

-- Associate Skills with Java Developer
INSERT IGNORE INTO career_role_skills (career_role_id, skill_id)
SELECT cr.id, s.id
FROM career_roles cr, skills s
WHERE cr.name = 'Java Developer'
AND s.name IN ('Java', 'Spring Boot', 'Spring Security', 'MySQL', 'PostgreSQL', 'REST API', 'JWT', 'Maven', 'Hibernate', 'JPA', 'Git', 'Microservices');

-- Associate Skills with Data Analyst
INSERT IGNORE INTO career_role_skills (career_role_id, skill_id)
SELECT cr.id, s.id
FROM career_roles cr, skills s
WHERE cr.name = 'Data Analyst'
AND s.name IN ('Python', 'Pandas', 'NumPy', 'SQL', 'MySQL', 'PostgreSQL', 'Data Visualization', 'Machine Learning', 'Git');

-- Associate Skills with Backend Developer
INSERT IGNORE INTO career_role_skills (career_role_id, skill_id)
SELECT cr.id, s.id
FROM career_roles cr, skills s
WHERE cr.name = 'Backend Developer'
AND s.name IN ('Java', 'Spring Boot', 'Node.js', 'REST API', 'MySQL', 'PostgreSQL', 'JWT', 'Git', 'Docker', 'Microservices');
