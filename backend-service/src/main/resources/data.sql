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

-- Assign READ permission to USER role
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM role r, permission p
WHERE r.role = 'USER' AND p.permission = 'READ'
AND NOT EXISTS (
    SELECT 1 FROM role_permission rp
    JOIN role r2 ON rp.role_id = r2.id
    JOIN permission p2 ON rp.permission_id = p2.id
    WHERE r2.role = 'USER' AND p2.permission = 'READ'
);
