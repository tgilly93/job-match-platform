-- =====================================
--     QUERIES TO TEST SEED DATA
-- =====================================
SELECT * FROM users;
SELECT * FROM skills;
SELECT * FROM jobs;
SELECT * FROM user_skills;
-- =====================================
--       BASIC MATCH QUERY
-- =====================================
SELECT 
    j.job_id,
    j.title,
    COUNT(*) AS matching_skills
FROM users u
JOIN user_skills us ON u.user_id = us.user_id
JOIN job_skills js ON us.skill_id = js.skill_id
JOIN jobs j ON js.job_id = j.job_id
WHERE u.user_id = 1
GROUP BY j.job_id, j.title
ORDER BY matching_skills DESC;
SELECT * FROM job_skills;
-- =====================================
--     PERCENTAGE-BASED MATCH QUERY
-- =====================================
SELECT 
    j.job_id,
    j.title,
    COUNT(us.skill_id) AS matching_skills,
    COUNT(js.skill_id) AS total_required_skills,
    ROUND(
        (COUNT(us.skill_id) * 100.0 / COUNT(js.skill_id)), 
        2
    ) AS match_percentage
FROM jobs j
JOIN job_skills js ON j.job_id = js.job_id
LEFT JOIN user_skills us 
    ON js.skill_id = us.skill_id 
    AND us.user_id = 1
WHERE j.min_experience <= 1
GROUP BY j.job_id, j.title
ORDER BY match_percentage DESC;
-- =====================================
--      WEIGHTED MATCH SCORE QUERY
-- =====================================
SELECT 
    j.job_id,
    j.title,

    -- Total importance of job skills
    SUM(js.importance_level) AS total_weight,

    -- Matched skill weight
    SUM(
        CASE 
            WHEN us.skill_id IS NOT NULL THEN js.importance_level
            ELSE 0
        END
    ) AS matched_weight,

    -- Weighted match percentage
    ROUND(
        SUM(
            CASE 
                WHEN us.skill_id IS NOT NULL THEN js.importance_level
                ELSE 0
            END
        ) * 100.0 / SUM(js.importance_level),
        2
    ) AS match_percentage

FROM jobs j
JOIN job_skills js ON j.job_id = js.job_id

LEFT JOIN user_skills us 
    ON js.skill_id = us.skill_id 
    AND us.user_id = 1

GROUP BY j.job_id, j.title
ORDER BY match_percentage DESC;

--MISSING SKILLS QUERY
SELECT 
    j.title,
    s.name AS missing_skill,
    js.importance_level

FROM jobs j
JOIN job_skills js ON j.job_id = js.job_id
JOIN skills s ON js.skill_id = s.skill_id

LEFT JOIN user_skills us 
    ON js.skill_id = us.skill_id 
    AND us.user_id = 1

WHERE us.skill_id IS NULL
ORDER BY j.title, js.importance_level DESC;