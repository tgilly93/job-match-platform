-- INSERT SKILLS
INSERT INTO skills (name) VALUES
('Java'),
('Spring Boot'),
('React'),
('SQL'),
('JavaScript'),
('HTML'),
('CSS'),
('Docker');

-- INSERT USER
INSERT INTO users (name, email, years_experience, desired_role, location)
VALUES ('Terry Gilmore', 'terry@example.com', 1, 'Software Engineer', 'Columbus, OH');

-- LINK USER TO SKILLS
INSERT INTO user_skills (user_id, skill_id, proficiency_level) VALUES
(1, 1, 4), -- Java
(1, 2, 3), -- Spring Boot
(1, 3, 3), -- React
(1, 4, 4), -- SQL
(1, 5, 3); -- JavaScript

-- INSERT JOBS
INSERT INTO jobs (title, company_name, location, description, min_experience, max_experience, source, url)
VALUES
('Junior Software Engineer', 'TechCorp', 'Remote', 'Looking for Java and Spring Boot developers', 0, 2, 'greenhouse', 'http://example.com/job1'),
('Frontend Developer', 'WebWorks', 'Remote', 'React and JavaScript required', 0, 2, 'lever', 'http://example.com/job2');

-- LINK JOBS TO SKILLS
INSERT INTO job_skills (job_id, skill_id, importance_level) VALUES
(1, 1, 5), -- Java
(1, 2, 5), -- Spring Boot
(1, 4, 4), -- SQL
(2, 3, 5), -- React
(2, 5, 5), -- JavaScript
(2, 6, 3); -- HTML