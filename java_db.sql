-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 08 jan. 2023 à 22:35
-- Version du serveur :  10.4.19-MariaDB
-- Version de PHP : 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java-chatapp`
--

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comments`
--

INSERT INTO `comments` (`id`, `content`, `user_id`, `post_id`, `created_at`) VALUES
(4, 'good post', 1, 17, '2022-12-22 13:46:57');

-- --------------------------------------------------------

--
-- Structure de la table `friend`
--

CREATE TABLE `friend` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `friend`
--

INSERT INTO `friend` (`id`, `user_id`, `friend_id`, `create_at`) VALUES
(1, 1, 2, '2022-12-21 19:30:59'),
(2, 1, 3, '2022-12-21 20:22:40'),
(4, 2, 7, '2022-12-22 12:50:53'),
(5, 2, 4, '2022-12-22 12:52:52'),
(6, 1, 3, '2022-12-22 13:35:07'),
(7, 1, 6, '2022-12-22 13:48:30');

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `like_at` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `title` varchar(250) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `img` varchar(200) DEFAULT NULL,
  `likes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `posts`
--

INSERT INTO `posts` (`id`, `title`, `content`, `user_id`, `created_at`, `img`, `likes`) VALUES
(12, 'Artificial intelligence (AI) ', 'Artificial intelligence (AI) is the development of computer systems that can perform tasks that would normally require human intelligence, such as learning, problem-solving, and decision-making', 7, '2022-12-22 13:47:28', 'C:\\Users\\HP 840\\..images_posts\\ai.jpg', 1),
(13, 'Web development', 'development is the process of building and maintaining websites. It involves a combination of front-end development, which refers to the design and user experience of a website, and back-end development, which involves the underlying server-side processes that support the functionality of the website.\nWeb development requires a combination of technical skills and creative problem-solving abilities. It is a constantly evolving field, with new technologies and approaches emerging all the time.\n\n\n\n', 1, '2022-12-22 12:22:53', 'C:\\Users\\HP 840\\..images_posts\\web5.jpg', 0),
(14, 'Front-end web development', 'Front-end web development typically involves using HTML, CSS, and JavaScript to build the user interface and layout of a website. HTML (HyperText Markup Language) is used to structure the content of a website, CSS (Cascading Style Sheets) is used to style the appearance of the website, and JavaScript is used to add interactivity and dynamic behavior to the website.', 1, '2022-12-22 12:23:53', 'C:\\Users\\HP 840\\..images_posts\\web1.jpg', 0),
(15, 'Back-end web development', 'Back-end web development involves developing the server-side processes that support the functionality of the website. This can include tasks such as setting up a database to store and retrieve data, implementing server-side logic to handle user input and requests, and integrating the website with external APIs (Application Programming Interfaces) to access external data and services.', 1, '2022-12-22 12:24:41', 'C:\\Users\\HP 840\\..images_posts\\web2.jpg', 0),
(17, 'chat app', 'A chat app is a software application that allows users to communicate with each other in real-time, typically through a chat interface or instant messaging. Chat apps can be used for a variety of purposes, including personal and group communication, customer service, and team collaboration.\nThere are many different chat apps available, ranging from simple messaging apps to more advanced platforms that offer additional features such as file sharing, voice and video calling, and integration with other tools and services. Some popular chat apps include WhatsApp, Facebook Messenger, WeChat, and Slack.', 2, '2022-12-22 12:49:04', 'C:\\Users\\HP 840\\..images_posts\\chat.png', 0),
(18, 'new post ', 'gi 2', 1, '2022-12-22 13:47:59', 'C:\\Users\\HP 840\\..images_posts\\web4.png', 0);

-- --------------------------------------------------------

--
-- Structure de la table `saves`
--

CREATE TABLE `saves` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `saved_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tel` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `tel`) VALUES
(1, 'mouhcine', 'mouhcine@gmail.com', '1234', '0625251719'),
(2, 'yassine', 'yassin@gmail.com', '1234', '0627292920'),
(3, 'redouane', 'redouane@gmail.com', '1234', ''),
(4, 'ahmed', 'ahmed@gmail.com', '1234', ''),
(6, 'ahmed', 'ahmed@gmail.com', '1234', ''),
(7, 'karim', 'karim@gmail.com', '1234', '0627282922'),
(8, 'hassane', 'hassane@gmail.com', '1234', '063832929');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_comment_fk` (`user_id`),
  ADD KEY `post_comment_fk` (`post_id`);

--
-- Index pour la table `friend`
--
ALTER TABLE `friend`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_fk` (`user_id`),
  ADD KEY `friend_id` (`friend_id`);

--
-- Index pour la table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `us_lfk` (`user_id`),
  ADD KEY `ps_lsk` (`post_id`);

--
-- Index pour la table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ps_fk` (`user_id`);

--
-- Index pour la table `saves`
--
ALTER TABLE `saves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `us_fk` (`user_id`),
  ADD KEY `ps_fsk` (`post_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `friend`
--
ALTER TABLE `friend`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `saves`
--
ALTER TABLE `saves`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `post_comment_fk` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_comment_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `friend`
--
ALTER TABLE `friend`
  ADD CONSTRAINT `friend_id` FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `ps_lsk` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `us_lfk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `ps_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `saves`
--
ALTER TABLE `saves`
  ADD CONSTRAINT `ps_fsk` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `us_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
