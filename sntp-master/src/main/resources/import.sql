REPLACE INTO `Gare` (`id_gare`, `nom_gare`)
VALUES (1, 'Paris Gare de Lyon'),
       (2, 'Gare d\'Angoulême '),
       (3, 'Bordeaux Saint-Jean');

REPLACE INTO `Itineraire` (`id_itineraire`, `depart_id_gare`, `terminus_id_gare`)
VALUES (1, 1, 3),
       (2, 3, 1),
       (3, 1, 3),
       (4, 3, 1);

REPLACE INTO `LigneDeTrain` (`id_ligne_de_train`, `nom_ligne`, `itineraire_id_itineraire`)
VALUES (1, 'Paris-Bordeau', 1),
       (2, 'Bordeaux-Paris', 2),
       (3, 'Paris-Bordeaux Direct', 3),
       (4, 'Bordeaux-Paris Direct', 4);

REPLACE INTO `Gare_Concerner_Itineraire` (`id_gare`, `id_itineraire`, `position`, `doit_marquer_arret`)
VALUES (2, 1, 1, 1),
       (2, 2, 1, 1),
       (2, 3, 1, 0),
       (2, 4, 1, 0);

REPLACE INTO `Train` (`train_id`, `nombre_de_place`, `numero_de_train`, `type_de_train`,
                      `ligne_de_train_id_ligne_de_train`)
VALUES (1, 35, 3465, 'TER', 1),
       (2, 54, 2567, 'TGV', 2),
       (3, 67, 5632, 'TGV', 3),
       (4, 30, 3687, 'TGV', 4);

REPLACE INTO `PlanificationDesTrain` (`id_planification_des_train`, `train_concerne_id`)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

REPLACE INTO `Planification_Station_Fixe` (`id_planification_des_train`, `id_gare`, `heureDepart`, `heureArrivee`)
VALUES (1, 1, '08:00:00', NULL),
       (1, 2, '09:05:00', '09:00:00'),
       (1, 3, NULL, '09:30:00'),
       (2, 1, NULL, '12:25:00'),
       (2, 2, '11:30:00', '11:25:00'),
       (2, 3, '11:00:00', NULL),
       (3, 1, '12:00:00', NULL),
       (3, 3, NULL, '13:10:00'),
       (4, 1, NULL, '16:10:00'),
       (4, 3, '15:00:00', NULL);

REPLACE INTO `Passage` (`id_Passage`, `date_de_passage`, `heure_de_depart`, `heure_arrivee`, `numero_de_quai`,
                        `doit_marquer_arret`, `id_planification_des_train`, `id_gare`)
VALUES (1, '2022-01-01', '09:00:00', NULL, 1, 1, 1, 1);

