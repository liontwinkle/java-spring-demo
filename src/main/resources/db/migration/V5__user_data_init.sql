INSERT INTO users (email, role_value, enabled, created_at) VALUES
  ('admin@admin@com', 'ADMIN', true, now()),
  ('user@user@com', 'USER', true, now());

INSERT INTO user_credentials(password_string, user_id, email) VALUES
  ('$2a$16$dXx7Kg6RBKg2Bo6YAVFI2uF1OgFhzAhHnphNkhRYuXovTTPYebSFC', (SELECT id from users where email = 'admin@admin@com'), 'admin@admin@com'), --Admin123@
  ('$2a$16$WjYLbjan5oeBXyFIyWLoF.Q5bJ.mjQM2w2gy2jFFuSM4mmaexBnbe', (SELECT id from users where email = 'user@user@com'), 'user@user@com'); --User123@