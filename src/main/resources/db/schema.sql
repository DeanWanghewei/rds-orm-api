create table if not exists db_store
(
    name                   VARCHAR PRIMARY KEY,
    jdbc_url               VARCHAR,
    jdbc_username          VARCHAR,
    jdbc_password          VARCHAR,
    jdbc_driver_class_name VARCHAR,
    jdbc_type              VARCHAR
);