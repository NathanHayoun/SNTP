REPLACE INTO `Gare`(`id_gare`, `nom_gare`)
VALUES (1, 'ParisGaredeLyon'),
       (2, 'Gared\'Angoulême'),
       (3, 'BordeauxSaint-Jean');
REPLACE INTO `Itineraire`(`id_itineraire`)
VALUES (1),
       (2),
       (3),
       (4);
REPLACE INTO `LigneDeTrain`(`id_ligne_de_train`, `nom_ligne`)
VALUES (1, 'Paris-Bordeau'),
       (2, 'Bordeaux-Paris'),
       (3, 'Paris-BordeauxDirect'),
       (4, 'Bordeaux-ParisDirect');
REPLACE INTO `Train`(`id_train`, `nombre_de_place`, `numero_de_train`, `type_de_train`, `id_itineraire`,`ligne_de_train_id_ligne_de_train`)
VALUES (1, 35, 3465, 'TER', 1, 1),
       (2, 54, 2567, 'TGV', 2, 2),
       (3, 67, 5632, 'TGV', 3, 3),
       (4, 30, 3687, 'TGV', 4, 4);
REPLACE INTO `Arret`(`id_gare`, `id_itineraire`, `doit_marquer_arret`, `heureArrivee`, `heureDepart`, `position`)
VALUES (1, 1, 1, NULL, '08:00:00', 1),
       (1, 2, 1, '11:05:00', NULL, 1),
       (1, 3, 1, NULL, '13:00:00', 1),
       (1, 4, 1, '18:00:00', NULL, 3),
       (2, 1, 1, '08:30:00', '08:35:00', 2),
       (2, 2, 1, '10:30:00', '10:35:00', 2),
       (2, 3, 0, '13:30:00', '13:30:00', 2),
       (2, 4, 0, '17:30:00', '17:30:00', 2),
       (3, 1, 1, '09:05:00', NULL, 3),
       (3, 2, 1, NULL, '10:00:00', 1),
       (3, 3, 1, '14:00:00', NULL, 3),
       (3, 4, 1, NULL, '17:00:00', 1);
REPLACE INTO `Passage`(`id_passage`, `date_de_passage`, `heure_arrivee_reel`, `heure_depart_reel`, `marquer_arret`,`numero_de_quai`, `id_gare`, `id_itineraire`)
VALUES (1, '2022-01-02', NULL, '08:05:00', NULL, 2, 1, 1),
       (2, '2022-01-03', NULL, '08:00:00', NULL, 5, 1, 1),
       (7, '2022-01-02', '08:40:00', '08:45:00', NULL, 1, 2, 1),
       (8, '2022-01-03', '08:30:00', '00:00:00', NULL, 2, 2, 1),
       (9, '2022-01-02', '09:15:00', NULL, NULL, 3, 3, 1),
       (10, '2022-01-03', '09:05:05', NULL, NULL, 2, 3, 1);