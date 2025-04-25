-- Create users table
CREATE TABLE IF NOT EXISTS public.USERMETADATA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    profile_picture_url VARCHAR(255) NOT NULL
);

-- Inserting into the public.USERMETADATA table
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (1, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (2, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (3, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (4, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (5, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (6, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (7, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (8, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (9, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png');
INSERT INTO public.USERMETADATA (user_id, profile_picture_url) VALUES (10, 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png');