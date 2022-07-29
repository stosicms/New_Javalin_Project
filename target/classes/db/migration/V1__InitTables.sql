CREATE TABLE "User" (
   id uuid PRIMARY KEY NOT NULL DEFAULT gen_random_uuid (),
   username TEXT NOT NULL,
   email TEXT NOT NULL UNIQUE,
   salt TEXT NOT NULL,
   password TEXT NOT NULL
);


CREATE TABLE "Book" (
   isbn TEXT PRIMARY KEY NOT NULL,
   title TEXT NOT NULL,
   author TEXT NOT NULL
);

INSERT INTO "User" (id, username, email, salt, password)
VALUES ('00000000-bead-4e5d-abff-000000000001', 'perwendel', 'pecko@gmail.com', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi'),
('00000000-bead-4e5d-abff-000000000002', 'davidase', 'david@gmail.com', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi'),
('00000000-bead-4e5d-abff-000000000003', 'federico', 'pederico@gmail.com', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi');

INSERT INTO "Book" (isbn, title, author)
VALUES ('9789583001215', 'Moby Dick', 'Herman Melville'),
('9780141324524', 'A Christmas Carol', 'Charles Dickens');


