CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL UNIQUE, -- login в нашей системе
    password TEXT NOT NULL, -- хэш пароля в нашей системе
    account TEXT NOT NULL UNIQUE, -- номер аккаунта (из импортируемого файла)
    name TEXT NOT NULL, -- имя из импортируемого файла
    -- TODO: дата рождения (сами решаете в каком формате и какие ограничения добавить,
    --  ключевое требование - хотелось бы потом иметь возможность с помощью функций даты и времени считать статистику)
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);