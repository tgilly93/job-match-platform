-- Drop tables if they exist (for clean reruns)
DROP TABLE IF EXISTS job_skills;
DROP TABLE IF EXISTS user_skills;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS skills;

-- USERS TABLE
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    years_experience INT DEFAULT 0,
    desired_role VARCHAR(100),
    location VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SKILLS TABLE
CREATE TABLE skills (
    skill_id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- USER_SKILLS (JOIN TABLE)
CREATE TABLE user_skills (
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    skill_id INT REFERENCES skills(skill_id) ON DELETE CASCADE,
    proficiency_level INT CHECK (proficiency_level BETWEEN 1 AND 5),
    PRIMARY KEY (user_id, skill_id)
);

-- JOBS TABLE
CREATE TABLE jobs (
    job_id SERIAL PRIMARY KEY,
    title VARCHAR(200),
    company_name VARCHAR(200),
    location VARCHAR(100),
    description TEXT,
    min_experience INT,
    max_experience INT,
    source VARCHAR(50),
    url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- JOB_SKILLS (JOIN TABLE)
CREATE TABLE job_skills (
    job_id INT REFERENCES jobs(job_id) ON DELETE CASCADE,
    skill_id INT REFERENCES skills(skill_id) ON DELETE CASCADE,
    importance_level INT CHECK (importance_level BETWEEN 1 AND 5),
    is_required BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (job_id, skill_id)
);