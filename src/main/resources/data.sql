insert into publisher values(1, 'test');

insert into book values(1, 1, 'opis', 'The Tartar Steppe');
insert into book values(2, 1, 'opis', 'Poem Strip');
insert into book values(3, 1, 'opis', 'Restless Nights: Selected Stories of Dino Buzzati');

insert into author values(1, 'test', 'test');
insert into author values(2, 'testa', 'testa');
insert into author values(3, 'testaa', 'testaa');

insert into book_author values(1,1);
insert into book_author values(2,1);
insert into book_author values(3,1);
insert into book_author values(2,2);
insert into book_author values(2,3);


INSERT INTO user_table(id, password, username) VALUES (1, '$2a$10$51iciojYD6a07jX.fo.yBuoHkmXUWnu3QveBD4I/eW7IYh0788E2C', 'user');