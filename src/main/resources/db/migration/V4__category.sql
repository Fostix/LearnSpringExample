drop table if exists category;
drop table if exists beer_category;

create table category (
	id VARCHAR(36) NOT NULL PRIMARY KEY,
	description VARCHAR(50),
	created_date TIMESTAMP,
	last_modified_date DATETIME(6) DEFAULT NULL,
	version	BIGINT DEFAULT NULL
) ENGINE = InnoDB;

create table beer_category (
	beer_id VARCHAR(36) NOT NULL,
	category_id VARCHAR(36) NOT NULL,
	PRIMARY KEY (beer_id, category_id),
	CONSTRAINT pc_beer_id_fk FOREIGN KEY (beer_id) REFERENCES beer (id),
	CONSTRAINT pc_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE = InnoDB;