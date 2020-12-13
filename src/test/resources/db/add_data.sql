INSERT INTO SERVICES (id, all_hours, english_speaking, entities, pi, premium, vip) VALUES ('4', FALSE, FALSE, TRUE, TRUE, FALSE, FALSE)
INSERT INTO SERVICES (id, all_hours, english_speaking, entities, pi, premium, vip) VALUES ('5', TRUE, FALSE, TRUE, TRUE, FALSE, TRUE)
INSERT INTO SERVICES (id, all_hours, english_speaking, entities, pi, premium, vip) VALUES ('6', FALSE, FALSE, TRUE, TRUE, FALSE, TRUE)

INSERT INTO FACILITY (services_id, company_id, is_online, is_visible, company_en, company_ru, company_ua, id, type, working_hours) VALUES ('4', '31', TRUE, TRUE, 'First municipal', 'Перше міське', 'Первое город', '218', 'city', 'hours' )
INSERT INTO FACILITY (services_id, company_id, is_online, is_visible, company_en, company_ru, company_ua, id, type, working_hours) VALUES ('5', '30', TRUE, TRUE, 'Second municipal', 'Второе міське', 'Друге город', '212', 'megapolice', 'hours' )
INSERT INTO FACILITY (services_id, company_id, is_online, is_visible, company_en, company_ru, company_ua, id, type, working_hours) VALUES ('6', '33', TRUE, TRUE, 'First municipal', 'Четверте міське', 'Четверте город', '214', 'city', 'hours' )
