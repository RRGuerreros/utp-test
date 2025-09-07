insert into users(username, password, role) values ('raphael', '$2a$10$2zhZjOlSJpHKnFj7aqC5hO3XbFLHlOWIaU1CHtllA4DhFfzzJrmMW', 'USER')
insert into users(username, password, role) values ('jose', '$2a$10$2zhZjOlSJpHKnFj7aqC5hO3XbFLHlOWIaU1CHtllA4DhFfzzJrmMW', 'USER')

insert into tags(name) values ('Java')
insert into tags(name) values ('Python')
insert into tags(name) values ('PHP')
insert into tags(name) values ('Excel')

insert into notes(user_id, content, title) values (1, 'la programacion es...', 'Programacion')
insert into notes(user_id, content, title) values (1, 'Java es un lenguaje de...', 'Java')
insert into notes(user_id, content, title) values (2, 'Excel una herramienta para...', 'Excel')

insert into notes_tags(note_id, tag_id) values (1, 1)
insert into notes_tags(note_id, tag_id) values (1, 2)
insert into notes_tags(note_id, tag_id) values (1, 3)
insert into notes_tags(note_id, tag_id) values (2, 1)
insert into notes_tags(note_id, tag_id) values (3, 4)
