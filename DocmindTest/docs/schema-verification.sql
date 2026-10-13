-- Run in MySQL Workbench after DocMind starts successfully.

-- 1. Confirm the database tables exist.
SELECT table_name
FROM information_schema.tables
WHERE table_schema = DATABASE()
ORDER BY table_name;

-- 2. Verify foreign keys generated from the JPA relationships.
SELECT
    kcu.TABLE_NAME AS table_name,
    kcu.COLUMN_NAME AS column_name,
    kcu.REFERENCED_TABLE_NAME AS referenced_table,
    kcu.REFERENCED_COLUMN_NAME AS referenced_column
FROM information_schema.KEY_COLUMN_USAGE kcu
WHERE kcu.CONSTRAINT_SCHEMA = DATABASE()
  AND kcu.REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY kcu.TABLE_NAME, kcu.COLUMN_NAME;

-- 3. Verify BCrypt password hashing without exposing password values.
SELECT id, username, email,
       CASE
         WHEN password LIKE '$2a$%' OR password LIKE '$2b$%' OR password LIKE '$2y$%'
         THEN 'BCrypt hash detected'
         ELSE 'Check password encoder / stored value'
       END AS password_storage_check
FROM users;
