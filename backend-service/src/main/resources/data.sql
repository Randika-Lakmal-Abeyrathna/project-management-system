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

-- Associate roles with permissions
-- ADMIN role gets all permissions (READ, WRITE, DELETE)
INSERT INTO role_permission (id, role_id, permission_id, created_at, updated_at)
SELECT gen_random_uuid(), r.id, p.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM role r, permission p
WHERE r.role = 'ADMIN' AND p.permission IN ('READ', 'WRITE', 'DELETE')
AND NOT EXISTS (
    SELECT 1 FROM role_permission rp
    WHERE rp.role_id = r.id AND rp.permission_id = p.id
);

-- USER role gets only READ permission
INSERT INTO role_permission (id, role_id, permission_id, created_at, updated_at)
SELECT gen_random_uuid(), r.id, p.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM role r, permission p
WHERE r.role = 'USER' AND p.permission = 'READ'
AND NOT EXISTS (
    SELECT 1 FROM role_permission rp
    WHERE rp.role_id = r.id AND rp.permission_id = p.id
);
