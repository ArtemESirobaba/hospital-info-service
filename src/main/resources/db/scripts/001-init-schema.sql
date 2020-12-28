--liquibase formatted sql
--changeset artem.sirobaba:2020-12-28-init-schema context: test-project

create SEQUENCE IF NOT EXISTS public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

create TABLE IF NOT EXISTS facility
(
    company_id     INTEGER,
    is_online      BOOLEAN,
    is_visible     BOOLEAN,
    company_en     VARCHAR(255),
    company_ru     VARCHAR(255),
    company_ua     VARCHAR(255),
    id             INTEGER,
    type           VARCHAR(255),
    working_hours  VARCHAR(255),
    services_id    INTEGER NOT NULL,
    PRIMARY KEY (services_id)
);

create TABLE IF NOT EXISTS services
(
   id  INTEGER NOT NULL,
   all_hours BOOLEAN,
   english_speaking BOOLEAN,
   entities BOOLEAN,
   pi BOOLEAN,
   premium BOOLEAN,
   vip BOOLEAN,
   PRIMARY KEY (id)
);
ALTER TABLE facility DROP CONSTRAINT IF EXISTS fk_facility_services;
ALTER TABLE facility ADD CONSTRAINT fk_facility_services FOREIGN KEY (services_id) REFERENCES services;

--rollback not required;
