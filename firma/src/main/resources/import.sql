INSERT INTO firma (fid, banka_port, fname, fadresa, fbroj_racuna, fpoziv_na_broj, fmodel, fpib, version) VALUES (1, '8080', 'Best firma1 ever', 'Djure Danicica 45', '111-1111111111111-11', '11111111111111111111', 97, '123', 1);
INSERT INTO firma (fid, banka_port, fname, fadresa, fbroj_racuna, fpoziv_na_broj, fmodel, fpib, version) VALUES (2, '8081', 'Best firma2 ever', 'Vojvodjanskih brigada 23', '111-1111111111111-33', '11111111111111111111', 97, '234', 1);
INSERT INTO firma (fid, banka_port, fname, fadresa, fbroj_racuna, fpoziv_na_broj, fmodel, fpib, version) VALUES (3, '8082', 'Best firma3', 'Bulevar Oslobodjenja 150', '111-1111111111111-55', '11111111111111111111', 97, '154', 1);

INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (1,'USLUGA', 'Donji ves', 'kg', 100, 10, 20, 1, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (2,'ROBA', 'Lizalice', 'g', 150, 5, 34, 1, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (3,'ROBA', 'Jagone', 'kg', 500, 10, 1000, 2, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (4,'ROBA', 'Banane', 'kg', 280, 15, 1500, 2, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (5,'ROBA', 'Breskve', 'kg', 120, 5, 130, 2, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (6,'ROBA', 'Kajsije', 'kg', 180, 7, 80, 2, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (7,'USLUGA', 'Kredit home', 'din', 10000, 5, 5, 3, 1);
INSERT INTO proizvod (pid, ptip, pname, pmera, pcena, prabat, pkolicina, fid, version) VALUES (8,'USLUGA', 'Kredit car', 'din', 20000, 10, 10, 3, 1);

INSERT INTO appuser (uuname, upword, fid, version) VALUES ('Dick', 'Steele', 1, 1);
INSERT INTO appuser (uuname, upword, fid, version) VALUES ('Random', 'Random', 2, 1);
