INSERT INTO `permessi`(`tipo_permesso`)
SELECT 'ADMIN'
WHERE NOT EXISTS(
SELECT 1 FROM permessi where tipo_permesso='ADMIN'
);

INSERT INTO `permessi`(`tipo_permesso`)
SELECT 'USER'
WHERE NOT EXISTS(
SELECT 1 FROM permessi where tipo_permesso='USER'
);