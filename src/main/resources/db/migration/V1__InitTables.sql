CREATE TABLE "User" (
   id BIGSERIAL PRIMARY KEY,
   username TEXT,
   salt TEXT,
   password TEXT
);


CREATE TABLE "Book" (
   isbn BIGINT PRIMARY KEY,
   title TEXT,
   author TEXT
);


INSERT INTO "User" (username, salt, password)
VALUES ('perwendel', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi'),
('davidase', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi'),
('federico', '$2a$10$ItiCBD2OlV6mUPXwSjjxIO', '$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi');


