-- Ñîçäàíèå òàáëèöû ïîëüçîâàòåëåé
CREATE TABLE users
(
    user_id      SERIAL PRIMARY KEY,
    username     VARCHAR(100)        NOT NULL,
    password     VARCHAR(100)        NOT NULL,
    email        VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20)
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL
);

CREATE TABLE user_roles
(
    u_id INT UNIQUE NOT NULL,
    r_id INT NOT NULL,

    FOREIGN KEY (u_id) REFERENCES users (user_id),
    FOREIGN KEY (r_id) REFERENCES roles (role_id)
);

-- Ñîçäàíèå òàáëèöû òîâàðîâ
CREATE TABLE items
(
    item_id SERIAL PRIMARY KEY,
    name    VARCHAR(100)   NOT NULL,
    price   DECIMAL(10, 2) NOT NULL
);

-- Ñîçäàíèå òàáëèöû êîðçèíû ïîëüçîâàòåëÿ
CREATE TABLE user_cart
(
    id       SERIAL PRIMARY KEY,
    user_id  INT REFERENCES users (user_id) NOT NULL,
    item_id  INT REFERENCES items (item_id) NOT NULL,
    quantity INT                            NOT NULL,
    CONSTRAINT fk_user_cart_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_cart_item FOREIGN KEY (item_id) REFERENCES items (item_id)
);

-- Ñîçäàíèå òàáëèöû èñòîðèè ïîêóïîê
CREATE TABLE purchase_history
(
    id            SERIAL PRIMARY KEY,
    user_id       INT REFERENCES users (user_id)      NOT NULL,
    item_id       INT REFERENCES items (item_id)      NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_purchase_history_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_purchase_history_item FOREIGN KEY (item_id) REFERENCES items (item_id)
);

--Insert data
INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
