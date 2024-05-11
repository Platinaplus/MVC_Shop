-- Ñîçäàíèå òàáëèöû ïîëüçîâàòåëåé
CREATE TABLE users
(
    user_id      SERIAL PRIMARY KEY,
    username     VARCHAR(100)        NOT NULL,
    password     VARCHAR(100)        NOT NULL,
    email        VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    role         VARCHAR(15)
);
-- Ñîçäàíèå òàáëèöû òîâàðîâ
CREATE TABLE products
(
    product_id SERIAL PRIMARY KEY,
    name    VARCHAR(100)   NOT NULL,
    price   DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100)   NOT NULL,
    image VARCHAR(100)   NOT NULL,
    length VARCHAR(100),
    width VARCHAR(100),
    material VARCHAR(100)
);

-- Ñîçäàíèå òàáëèöû êîðçèíû ïîëüçîâàòåëÿ
CREATE TABLE user_cart
(
    id       SERIAL PRIMARY KEY,
    user_id  INT REFERENCES users (user_id) NOT NULL,
    item_id  INT REFERENCES products (product_id) NOT NULL,
    quantity INT                            NOT NULL,
    CONSTRAINT fk_user_cart_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_cart_item FOREIGN KEY (item_id) REFERENCES products (product_id)
);

-- Ñîçäàíèå òàáëèöû èñòîðèè ïîêóïîê
CREATE TABLE purchase_history
(
    id            SERIAL PRIMARY KEY,
    user_id       INT REFERENCES users (user_id)      NOT NULL,
    item_id       INT REFERENCES products (product_id)      NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_purchase_history_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_purchase_history_item FOREIGN KEY (item_id) REFERENCES products (product_id)
);
