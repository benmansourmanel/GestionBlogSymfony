-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 04 mai 2023 à 21:47
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `khadamni`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nv_satif` varchar(230) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `fournisseur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nomc` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `usagepro` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `qualite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `fournisseur`, `nomc`, `usagepro`, `qualite`) VALUES
(1, 'mohamed', 'LG', 'tous', 'haute'),
(2, 'hp', 'hp', 'tous', 'haute'),
(3, 'steck', 'hp', 'tous', 'haute');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantite` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `libelle`, `quantite`) VALUES
(1, 'electronique', 200),
(3, 'bois', 400);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL,
  `article_id` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `name_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `article_id`, `content`, `created_at`, `name_user`) VALUES
(1, 2, 'incredible', NULL, 'sahbi'),
(2, 2, 'incredible', NULL, 'sahbi'),
(3, 2, 'good', NULL, 'sahbi'),
(4, 2, 'good', NULL, 'sahbi'),
(6, 2, 'whoaa', NULL, 'moutye'),
(7, 2, 'whoaa', NULL, 'moutye'),
(8, 5, 'nice', NULL, 'sahbi'),
(9, 2, '***', NULL, 'sahbi'),
(10, 5, 'good', NULL, 'emana'),
(11, 5, 'good', NULL, 'emana'),
(12, 2, 'motye', NULL, 'sahbi'),
(13, 2, '***', NULL, 'sahbi'),
(14, 6, 'emna', NULL, 'emana');

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230502002637', '2023-05-02 02:26:42', 92),
('DoctrineMigrations\\Version20230502004606', '2023-05-02 02:46:15', 129),
('DoctrineMigrations\\Version20230502012754', '2023-05-02 03:28:01', 115),
('DoctrineMigrations\\Version20230502013132', '2023-05-02 03:31:38', 20),
('DoctrineMigrations\\Version20230502020204', '2023-05-02 04:02:11', 48),
('DoctrineMigrations\\Version20230504025237', '2023-05-04 04:52:51', 148),
('DoctrineMigrations\\Version20230504143253', '2023-05-04 16:33:03', 55),
('DoctrineMigrations\\Version20230504152239', '2023-05-04 17:22:45', 59);

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE `materiel` (
  `id` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `nomm` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `quantite` int(11) NOT NULL,
  `valabilite` date NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`id`, `categorie_id`, `nomm`, `prix`, `quantite`, `valabilite`, `image`) VALUES
(1, 2, 'pc portable', 15, 20, '2018-01-01', 'Screenshot-2023-03-27-1-64506312e7436.png'),
(2, 1, 'new', 14, 20, '2021-01-20', 'Screenshot-2023-03-27-1-6452b5f1b6fa8.png'),
(3, 2, 'testfront', 20, 20, '2020-06-04', 'unnamed-64530e5aed536.png');

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `headers` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue_name` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id`, `title`, `content`, `image`, `created_at`, `update_at`) VALUES
(2, 'first post', 'The article begins by highlighting the pressing need for sustainable energy solutions due to the growing concerns of climate change and the limited availability of traditional energy sources. It underscores the importance of leveraging advanced technologies like AI to address these challenges effectively.\r\n\r\nAI in Energy Generation:\r\nThe first section explores how AI is reshaping energy generation. It discusses the utilization of AI algorithms in optimizing renewable energy sources such as solar, wind, and hydroelectric power. AI-powered predictive models and advanced data analytics techniques enable accurate forecasting, improving efficiency and output. The article also examines the integration of AI with emerging technologies like smart grids and energy storage systems for better energy management.\r\n\r\nAI in Energy Distribution and Grid Optimization:\r\nThe second section focuses on AI\'s role in energy distribution and grid optimization. It explains how AI algorithms and machine learning techniques enhance grid reliability, predictive maintenance, and demand-response systems. Smart grid applications powered by AI enable real-time monitoring, fault detection, and self-healing mechanisms, thereby improving energy efficiency and reducing wastage.\r\n\r\nAI in Energy Consumption and Demand Management:\r\nThe third section explores the impact of AI on energy consumption and demand management. It highlights how AI-driven systems, such as smart thermostats, energy analytics platforms, and intelligent appliances, facilitate energy optimization at the consumer level. These technologies empower users to make informed decisions regarding energy consumption, leading to cost savings and reduced environmental impact.', 'article1-6453ad0e5f21c.jpg', '0000-00-00 00:00:00', NULL),
(3, 'second post', 'The article acknowledges the challenges associated with integrating AI into the energy sector, including data privacy, cybersecurity, and ethical considerations. It emphasizes the importance of developing robust frameworks and regulations to address these concerns. Furthermore, the article discusses the opportunities for collaboration between industry stakeholders, policymakers, and researchers to drive innovation and adoption of AI in sustainable energy solutions.', 'palette-6453ad3d91ba6.jpg', '0000-00-00 00:00:00', NULL),
(4, 'article 3', 'we have a good article', 'Screenshot-2023-03-27-1-6453c3129b165.png', '2023-05-04 16:37:06', NULL),
(5, 'article 4', 'lololololololol', 'unnamed-6453e97a806df.png', '2023-05-04 19:20:58', NULL),
(6, 'article 4', 'dhsjhdjshdjs', 'nihed-6453ebdde767a.png', '2023-05-04 21:34:09', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `categories_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `categories_id`, `nom`, `image`, `prix`, `stock`) VALUES
(1, 1, 'pallete', '6450725ccc16e.png', 470, 800),
(2, 1, 'four', '64528aed8ad50.jpg', 1500, 200),
(7, 3, 'paleete', '64528fd50cfe6.jpg', 5000, 100);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `nom` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(220) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(210) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `cin`, `nom`, `prenom`, `mail`, `password`, `role`, `tel`) VALUES
(1, 19880013, 'attia', 'mohamed', 'attia2020123@gmail.com', '$2y$13$KE31wDWwdGNn2Z5dGwBs2uUVVDFtfa1ey9KvzEmuMn..gbbY0CZVm', 'admin', 53587130),
(2, 19880017, 'attia', 'nihed', 'nihed@gmail.com', '$2y$13$M50V/Pyp/qaLmYYbkkkBAeOPd6P1XwOt.pHF1rRm37/5xzjNVl5vu', 'client', 96445230),
(3, 12696225, 'khalfaoui', 'sahbi', 'sahbi@gmail.com', '$2y$13$KmgZVfbkoo0UKv8dFgZpNe7bCiuFQLaJ3TNoDw3cXsQfwEh1XrWz6', 'ouvrier', 96000002),
(4, 14587452, 'guecha', 'aziz', 'aziz.jellazi@gmail.com', '$2y$13$iH5jiYtNh7KfteSq.czgjOSFtYzqeI8OnachYOaD4kxtGzvCbfHW6', 'admin', 53587130),
(5, 12345678, 'test', 'test', 'test@gmail.com', '$2y$13$QQrY7kuwLhe4fVb5eaFp/OY3muDrBAgiTcXj3ofR57lLI7mTdLTdC', 'fourniseur', 53587130),
(6, 12345678, 'becha', 'moutye', 'moutye.becha@esprit.tn', '$2y$13$c6J.CVP91cKuCjW6hT8Lj.12CsNSU0AChobXJfMy35dhHO9wTxaxm', 'ouvrier', 96445230),
(7, 12345678, 'emna', 'emana', 'emna@esprit.tn', '$2y$13$/Lydzte/jjcBy2viENm1wuSY4zxU6LPZlvc3uxE0gSY1AxBgZ1lXq', 'fourniseur', 53587130);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_8F91ABF0A76ED395` (`user_id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_67F068BC7294869C` (`article_id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_18D2B091BCF5E72D` (`categorie_id`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_29A5EC27A21214B7` (`categories_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `materiel`
--
ALTER TABLE `materiel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FK_8F91ABF0A76ED395` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BC7294869C` FOREIGN KEY (`article_id`) REFERENCES `post` (`id`);

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `FK_18D2B091BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC27A21214B7` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
