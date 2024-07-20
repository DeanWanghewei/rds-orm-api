create table if not exists db_store
(
    name                   VARCHAR PRIMARY KEY,
    jdbc_url               VARCHAR,
    jdbc_username          VARCHAR,
    jdbc_password          VARCHAR,
    jdbc_driver_class_name VARCHAR,
    jdbc_type              VARCHAR
);

CREATE TABLE IF NOT EXISTS data_duplicate
(
    source    VARCHAR NOT NULL,
    md5_value VARCHAR NOT NULL,
    PRIMARY KEY (source, md5_value)
);