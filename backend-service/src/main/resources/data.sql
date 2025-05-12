-- Insert default roles if they don't exist
INSERT INTO role (id, role, created_at, updated_at)
SELECT gen_random_uuid(), 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM role WHERE role = 'ADMIN');

INSERT INTO role (id, role, created_at, updated_at)
SELECT gen_random_uuid(), 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM role WHERE role = 'USER');

-- Insert default permissions if they don't exist
INSERT INTO permission (id, permission, created_at, updated_at)
SELECT gen_random_uuid(), 'READ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM permission WHERE permission = 'READ');

INSERT INTO permission (id, permission, created_at, updated_at)
SELECT gen_random_uuid(), 'WRITE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM permission WHERE permission = 'WRITE');

INSERT INTO permission (id, permission, created_at, updated_at)
SELECT gen_random_uuid(), 'DELETE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM permission WHERE permission = 'DELETE');


