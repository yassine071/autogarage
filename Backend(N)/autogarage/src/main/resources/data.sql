insert into `user` (username,password, enabled)
values

('monteur','$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1),       -- password: password
('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1),    -- password: password
('kassa','$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1),       -- password: password
('back', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1)     -- password: password
 ON DUPLICATE KEY UPDATE username=username;


insert into `authority` (username, authority)
values
('monteur', 'ROLE_MONTEUR'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN'),
('kassa', 'ROLE_KASSA'),
('back', 'ROLE_BACK')
 ON DUPLICATE KEY UPDATE username=username;


insert into `action` (action, name, price) values
 ('Tyre is changed', 'tyre', '40'),
('oil changed','olie', '45'),
('new seat belt placed','gordel', '40');

insert into `car` (brand, costumer_name, license_plate, model) values
('Volkswagen', 'Jan', '45-AB-49', 'Golf'),
('Toyota', 'Ahmed', '42-WB-69', 'Yaris');

insert into `component` (component, price) values
('Tyre', '30'),
('Motor', '500');

insert into `examination` (appointment, costumer, date ) values
('Volkswagen', 'Jan', '11-11-2020' ),
('Toyota', 'Ahmed', '07-11-2020');

insert into `reparation` (date, status) values
('10-11-2020', 'bezig'),
('08-11-2020', 'gedaan');