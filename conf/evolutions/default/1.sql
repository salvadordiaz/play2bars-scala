# --- First database schema

# --- !Ups

create table "BAR" (
  id                        SERIAL PRIMARY KEY,
  name                      varchar(255) not null
);


CREATE SEQUENCE "s_BAR_id";

# --- !Downs

drop table if exists "BAR";
DROP SEQUENCE if exists "s_BAR_id";

