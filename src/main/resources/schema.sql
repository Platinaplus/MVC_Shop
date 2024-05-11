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

--insert products
insert into products values (1,'tourniquet','Mojito', 25.00,'', '42-47 cm, has an extension chain', '1.5 cm', 'beads, glass beads, metal accessories');
insert into products values (2,'brooches','Popcorn', 18.00,'', '7 cm', '10 cm', 'some accessories');
insert into products values (3,'tourniquet','Mojito2', 25.10,'https://crealandia.com/wp-content/uploads/2022/11/1-46-scaled.jpg', '42-47 cm, has an extension chain', '1.5 cm', 'beads, glass beads, metal accessories');
insert into products values (4,'brooches','Popcorn2', 100.00,'https://cdn11.bigcommerce.com/s-ceebc/images/stencil/1280x1280/products/2867/12942/Flaming_heart_brooch_on_black__11855.1656004063.jpg?c=2', '7 cm', '10 cm', 'some accessories')
