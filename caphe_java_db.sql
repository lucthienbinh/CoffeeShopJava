-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2020 at 01:39 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `caphe_java_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_vietnamese_ci NOT NULL,
  `mobilephone` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `address`, `mobilephone`, `email`) VALUES
(1, 'Dr. Elaina Gulgowski IV', '220 Chanel Inlet\nBartonton, MA 97215', '767-547-8923 x2178', 'bins.margie@windler.org'),
(2, 'Reynold Reichel', '3670 Gleichner Run Apt. 555\nWest Anashire, LA 81224-7984', '+1-232-414-8276', 'violette82@yahoo.com'),
(3, 'Ronny Paucek', '3861 Trantow Summit\nWest Cooperhaven, WY 05979-9592', '884.293.8319 x837', 'june52@anderson.net'),
(4, 'Miss Ebony Romaguera IV', '5318 Waelchi Wells Apt. 499\nPort Maya, LA 88598', '(567) 250-0944', 'hswift@labadie.com'),
(5, 'Mrs. Loren Lubowitz II', '4115 Shayne Freeway\nRandalton, NH 32305', '497.821.8662 x5784', 'nbatz@runolfsson.com'),
(6, 'Cecelia Hagenes', '65409 Douglas Coves Apt. 641\nMarquisview, CO 65862', '609.392.4605', 'emil28@yahoo.com'),
(7, 'Dr. Vernon Homenick', '322 Jaqueline Streets\nPort Asa, PA 26798-0319', '1-964-986-3420 x874', 'helen03@hotmail.com'),
(8, 'Mr. Martin Hane Jr.', '5204 Halie Forge\nAmirborough, NV 56037-0634', '754.759.6238', 'odessa14@hermiston.net'),
(9, 'Lillian Murazik', '791 Estevan Springs Apt. 428\nHauckside, NC 15230', '(715) 363-8186 x8871', 'louie.ullrich@hotmail.com'),
(10, 'Mr. Russel Roob', '19634 Frida Overpass\nRosamondview, KY 86323', '641-476-7265 x82636', 'zsenger@gmail.com'),
(11, 'Ray Hane', '4638 Zulauf Neck Apt. 329\nLake Bennie, SD 70420', '+1 (462) 644-6515', 'peter.mayert@gmail.com'),
(12, 'Kim Kautzer', '865 Wellington River\nPipermouth, AL 45072-8250', '597-266-8707', 'teresa32@terry.com'),
(13, 'Kieran Adams I', '2828 Reilly Brooks Apt. 903\nCalifurt, LA 84480', '561.896.7049 x07794', 'boyle.arch@hansen.com'),
(14, 'Dr. Sigmund Buckridge III', '4010 Michale Extensions\nNew Shaun, PA 78323', '+1-303-227-0611', 'deontae25@gmail.com'),
(15, 'Geovanny Leannon', '810 Batz Villages Apt. 202\nSouth Malliefurt, MN 88348-2241', '1-694-928-7641 x9767', 'sebastian64@weissnat.com'),
(16, 'Prof. Keven Tremblay PhD', '86385 Hagenes Lights\nFeilville, MT 54036', '878-840-2768 x70395', 'alanis43@hotmail.com'),
(17, 'Marcos Skiles', '7416 Karson Street Apt. 677\nCassinton, AL 83208-0140', '281-487-0364 x70693', 'kulas.brett@gmail.com'),
(18, 'Dr. Angelita Fahey DDS', '3462 Andreanne Corner\nWindlermouth, TN 31302-6329', '613-551-1170', 'grant.estella@gmail.com'),
(19, 'Verlie Miller', '3098 Rosalia Passage\nRunteton, MI 85584', '1-753-903-8038', 'bernard50@hotmail.com'),
(20, 'Odessa Hayes Jr.', '626 Lind Parkways\nReichelmouth, WI 75729', '1-363-849-6714 x5091', 'camila.goodwin@gmail.com'),
(21, 'Prof. Avery Williamson', '44941 Agustin Field Apt. 566\nLittlebury, DC 51021', '1-927-340-3128 x89769', 'moen.caden@yahoo.com'),
(22, 'Dasia Hickle', '2784 Ayla Pass Suite 318\nLake Marc, GA 23315-5767', '(539) 840-4377 x5096', 'schaefer.ursula@sporer.com'),
(23, 'Elizabeth Kuphal DVM', '97085 McClure Village Suite 782\nKautzerbury, IL 94711', '1-726-960-4340 x040', 'streich.adalberto@yahoo.com'),
(24, 'Pasquale Mitchell', '64162 Bergnaum Knolls Apt. 421\nCristview, VA 18408', '669.672.1476 x679', 'marlee41@hoeger.org'),
(25, 'Alia Jerde', '77711 Shanahan Mission\nEast Daxview, MA 64450', '231-702-8376 x63045', 'jennings.jacobs@padberg.biz'),
(26, 'Rickie Skiles', '2680 Hank Mount Suite 086\nBergeport, UT 96261-0305', '289-464-7600', 'mandy04@gmail.com'),
(27, 'Chris Herzog', '99249 Bridget Dale\nWestville, NY 22767', '584.723.1810 x29505', 'qgoldner@jerde.com'),
(28, 'Orlo Goodwin', '444 Feest Passage Suite 864\nEast Rylanville, NH 57504', '(489) 812-4739 x2773', 'bednar.clay@spinka.com'),
(29, 'Prof. Sydney Crona V', '2091 Norris Mission Suite 316\nLake Oceanechester, NH 23649-3253', '(869) 871-9904', 'bcollier@gmail.com'),
(30, 'Jennyfer Emmerich', '166 Rocky Coves Suite 440\nNew Danika, NJ 11803', '750.544.0002 x1701', 'brandt58@rodriguez.com'),
(31, 'Shemar Corkery', '89251 Paige Walks\nKilbackchester, GA 18540-3978', '(391) 770-9180 x365', 'batz.camron@haag.biz'),
(32, 'Anthony Mosciski', '666 Dejuan Radial\nLake Ruthie, ND 13520-6179', '+1.487.839.2578', 'walker.petra@gmail.com'),
(33, 'Luella Prosacco', '71607 Zoila Road Apt. 044\nLake Nanniehaven, PA 84254-9963', '519-294-7040', 'evie.ankunding@hotmail.com'),
(34, 'Garnett Ratke', '965 Dawson Pike Suite 593\nGarrisonport, LA 11072-7305', '872-437-7910 x5662', 'mclaughlin.susanna@yahoo.com'),
(35, 'Oceane Greenfelder', '8455 Brown Squares\nLucilefort, WA 56583-5048', '+1-625-831-0232', 'mherzog@yahoo.com'),
(36, 'Mr. Kay Conroy', '2393 Alden Brooks\nRathmouth, IN 67519-9757', '776-474-9522', 'olson.stephan@farrell.info'),
(37, 'Esther Kihn', '237 Talon Glens\nWest Ena, ME 19210', '1-825-597-6047 x324', 'gunnar63@rolfson.com'),
(38, 'Kaycee Waters', '74022 Considine Shoal Apt. 986\nLeuschkeview, AK 37143', '1-757-968-6137', 'daugherty.terry@pollich.biz'),
(39, 'Tiara Price', '170 Carolyne Inlet Suite 599\nPort Deanna, NV 99917-9481', '640.416.7831', 'shomenick@hotmail.com'),
(40, 'Edwina Schuppe Jr.', '469 Jamal Center\nSouth Domenica, KS 54480-7779', '840.821.9215 x581', 'wmckenzie@yahoo.com'),
(41, 'Dr. Trenton Kuvalis', '13956 Rempel Square Apt. 234\nSouth Aubree, TX 28545-6814', '671-872-6930 x45535', 'katharina.herzog@gmail.com'),
(42, 'Lester Spencer DDS', '55351 Lang Points\nTrevahaven, FL 74849', '1-781-210-3590', 'claire.cruickshank@hotmail.com'),
(43, 'Dr. Laila Larson', '190 Tyrel Lane\nEast Bradlyside, AR 74636-0568', '1-813-533-0691 x390', 'rshanahan@harber.com'),
(44, 'Dr. Armand Heidenreich IV', '62392 Lorine Brooks Apt. 355\nTheresechester, NV 75659-6419', '(308) 897-6441', 'shawn59@schoen.com'),
(45, 'Francisco Crist', '9897 Dooley Drive Suite 585\nEast Oniechester, DE 04828-5991', '949.893.2070', 'deckow.marilie@kris.com'),
(46, 'Miss Enola Aufderhar', '5453 Mosciski Rue Suite 975\nEast Rosemarieburgh, PA 90970', '1-683-550-8204 x53972', 'jettie.schmitt@satterfield.com'),
(47, 'Karine Senger', '430 Trantow Turnpike Suite 059\nMartinton, NJ 29549', '215-818-6655 x5428', 'marco42@gmail.com'),
(48, 'Lemuel Johnson', '20256 Mitchell Ferry\nBaumbachland, AR 78130-9690', '532-721-7600', 'nnader@yahoo.com'),
(49, 'Mohammed Steuber I', '68966 Geovanny Forest Suite 564\nDibbertborough, HI 71993-7952', '1-847-928-5005 x31016', 'okey82@schmidt.com'),
(50, 'Antonette Ruecker Jr.', '3791 Schoen Inlet Suite 620\nCorkeryborough, ND 22678', '297-388-0752', 'fgrady@hotmail.com'),
(51, 'Prof. Katelynn Wilkinson', '5558 Beier Lake Suite 422\nPasqualetown, KS 94090', '1-289-435-6136 x5059', 'bahringer.ahmad@yahoo.com'),
(52, 'Odessa Hayes', '47851 Raynor Row Suite 014\nCarmelochester, ME 52464-8858', '1-774-385-2532 x551', 'jschaden@gmail.com'),
(53, 'Prof. Myrna VonRueden Sr.', '98945 Uriel Squares Suite 598\nLake Bridgetport, NV 40418-2172', '1-359-872-7199 x74413', 'bruen.fiona@hotmail.com'),
(54, 'Verna Legros', '13964 Vivienne Well Suite 123\nLake Rudolphburgh, AL 55199-3337', '992.887.8128 x41764', 'ljohnston@labadie.org'),
(55, 'Duane Dooley', '115 Schroeder Summit\nSouth Albertha, NV 39712-2851', '824.252.7613', 'dorris45@bahringer.info'),
(56, 'Emely Lebsack', '3292 Jacky Creek Suite 343\nFletafurt, SD 17466', '+1.361.303.0670', 'verla.kiehn@yahoo.com'),
(57, 'Natalie Medhurst', '958 Schmeler Stream Apt. 891\nEast Timothy, CA 28503', '(732) 673-0799', 'mtremblay@gmail.com'),
(58, 'Ms. Carmela Effertz', '976 Yasmin Forges Apt. 082\nSouth Jasenville, ND 29400-0439', '+1-249-431-7192', 'jerod86@legros.com'),
(59, 'Mr. Ward Hermiston PhD', '3059 Jenkins Unions Suite 929\nEast Sylviachester, AZ 25162', '+1.820.486.0782', 'vmckenzie@gulgowski.com'),
(60, 'Kacey Green', '9569 Joany Wall Apt. 856\nNew Isobel, MD 03893', '260.880.9007 x0693', 'zmurphy@gmail.com'),
(61, 'Ken Wunsch Sr.', '4428 Daryl Mission Apt. 224\nWest Janahaven, MS 13310', '947.875.0774', 'ahayes@powlowski.com'),
(62, 'Irving Olson', '56616 Misty Viaduct\nEast Kelsieborough, NY 18949-8388', '743.705.2303', 'carlee93@jakubowski.com'),
(63, 'Dr. Monserrate Glover', '1450 Darren Circles\nNorth Destinchester, MD 96906-6512', '+19502157864', 'amparo50@hotmail.com'),
(64, 'Madisyn Rowe V', '8859 Ebony Overpass Apt. 838\nDietrichmouth, ID 03079-7567', '(264) 555-9881 x02112', 'qreinger@schneider.com'),
(65, 'Mr. Perry Dare', '3846 Schaefer Ville\nPort Aiyana, HI 52734', '(775) 559-2974', 'stowne@upton.info'),
(66, 'Roxanne Renner', '935 O\'Kon Greens\nSouth Jessicaberg, NM 49771-5563', '(317) 265-7373', 'allan63@yahoo.com'),
(67, 'Prof. Dallas Shields', '59287 Lonie Passage\nVeldamouth, KY 72757', '+1.896.296.0631', 'jacky.kshlerin@wehner.org'),
(68, 'Marcelle Smith', '856 Kirlin Land Apt. 056\nMarquesstad, DE 63244', '1-724-608-5828', 'wisozk.winnifred@tremblay.info'),
(69, 'Kamron Von', '3789 McClure Hills\nGleichnertown, VT 77857', '+1-727-493-5707', 'kris.raoul@gmail.com'),
(70, 'Garnet Kirlin', '86525 Steve Ville\nCharlotteburgh, RI 52085', '554.217.3053', 'lindgren.dana@gmail.com'),
(71, 'Adriel Runolfsdottir', '772 Danial Lakes Suite 324\nPort Ameliechester, WI 97714', '836-246-8182 x8133', 'heathcote.imani@yahoo.com'),
(72, 'Dr. Marvin Bernier', '499 Ardith Inlet Apt. 862\nLake Emmyville, OR 98388', '985.839.1846', 'ablock@beier.org'),
(73, 'Francisco Beatty', '16447 Kara Mountains\nSerenitymouth, MD 11667-1095', '552.563.9603 x826', 'uhansen@hotmail.com'),
(74, 'Dr. Dulce Schinner', '22617 Mya Walk\nEast Amelie, IA 49359-5824', '1-646-886-4532 x8101', 'chesley.wiegand@dooley.com'),
(75, 'Verna Parker', '8986 Veum Ports\nKaydenfurt, OH 84883-2324', '647-693-8198 x6880', 'ola.dickens@gmail.com'),
(76, 'Henderson Hackett MD', '133 Schinner Orchard Apt. 118\nLake Kirachester, NY 52366', '1-381-814-9735', 'hrosenbaum@stoltenberg.com'),
(77, 'Jose Fisher MD', '4199 Labadie Lights Suite 842\nLake Edgardo, AR 83510', '615-873-1729 x4684', 'lstreich@renner.net'),
(78, 'Ramiro Armstrong DDS', '96909 Hickle Views\nLake Howellland, AZ 76024', '(228) 286-1592', 'nkoch@mosciski.net'),
(79, 'Bonnie Corkery', '568 King Plaza Suite 992\nZiemebury, MO 70981-1467', '502.241.0063 x31876', 'sister.corwin@hintz.info'),
(80, 'Dr. Ralph Kozey', '110 Abbott Meadow\nNew Nils, DC 32462-8639', '1-540-357-4968 x770', 'bernier.jaeden@gmail.com'),
(81, 'Rusty Carter', '62224 Daniel Hollow\nNorth Alicemouth, WA 76572-9640', '1-938-561-0059 x373', 'darwin.gaylord@gmail.com'),
(82, 'Tanya Rice DDS', '15787 Paucek Village Apt. 421\nBradleytown, AZ 48859', '(467) 579-8659', 'powlowski.leslie@hartmann.org'),
(83, 'Demond Shanahan', '12780 Steuber Manor Suite 250\nLueilwitzmouth, WA 48516-4292', '+1-356-761-2457', 'cschuster@mertz.com'),
(84, 'Ernestina Johnson', '679 Willie Burg\nMichellefurt, ID 54325-6911', '(874) 613-7422 x9018', 'qsimonis@gibson.com'),
(85, 'Shanny Farrell', '6615 Beaulah Islands Suite 752\nWest Arnoldo, MO 46447', '+1 (831) 535-0259', 'reynolds.jade@gmail.com'),
(86, 'Nedra Pouros', '6525 Everett Ports\nWest Verlieview, IL 06492', '+1.734.910.5869', 'ullrich.kian@fahey.com'),
(87, 'Frederique Hermann', '2204 Tom Pine\nWest Rosemary, IN 10681', '1-901-942-2687 x5006', 'pcole@hotmail.com'),
(88, 'Ms. Lily Senger V', '9852 Ola Haven\nLake Howellview, MD 10638-3724', '805.299.2128 x99344', 'wallace.kuhlman@hotmail.com'),
(89, 'Jovanny Deckow IV', '2565 Howe Roads Apt. 895\nRoscoeton, NM 03547', '714.491.2816 x8110', 'marvin.cary@hotmail.com'),
(90, 'Annie Stroman III', '95066 Prince Court Apt. 157\nMattiechester, PA 26332-5352', '1-297-484-9230 x6561', 'doyle51@hotmail.com'),
(91, 'Mrs. Stella Gleichner', '66247 Jan Islands Suite 811\nQuitzonbury, KS 75104', '(417) 299-6480 x142', 'tremblay.name@heaney.com'),
(92, 'Dr. Addie Swift', '664 Becker Road Suite 664\nHattiefort, VA 01346', '890-413-8042 x3532', 'reina.stoltenberg@yahoo.com'),
(93, 'Miss Brigitte Lynch', '55057 Carroll Path\nVladimirville, VA 14178', '419-448-4678', 'hermann.tyrique@zulauf.com'),
(94, 'Jaden Moore', '475 Luciano Fords\nRobelfurt, CA 86161', '816.353.8203', 'schuster.jalon@yahoo.com'),
(95, 'Miss Elenor Lehner', '30607 West Stravenue\nDarebury, OR 07378-1790', '1-337-572-5596', 'genesis77@lueilwitz.com'),
(96, 'Dr. Nannie Heaney', '6500 Bryon Lock Apt. 111\nOndrickafort, ME 64702', '1-738-451-7217 x772', 'aschroeder@fisher.com'),
(97, 'Marjory Witting', '810 Dewitt Key\nEast Haskell, KS 58799', '354-722-5637', 'cwiza@yahoo.com'),
(98, 'Aletha Lockman', '1374 Hettie Circles Suite 620\nDedricfort, AL 54610', '1-408-529-2564 x73100', 'srohan@gmail.com'),
(99, 'Hoyt Pfeffer', '22754 Jazmin Mews\nSouth Israelhaven, NJ 49698-1985', '641.410.9607 x66985', 'kmorar@hotmail.com'),
(100, 'Jada Johnson', '284 Fritsch Forges Apt. 642\nLake Misaelborough, WV 26939', '(502) 992-0726 x2601', 'august.hirthe@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `price` int(11) NOT NULL,
  `remaining` int(11) NOT NULL,
  `unit` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `name`, `price`, `remaining`, `unit`) VALUES
(1, 'Trai Cam', 1000, 10, 'Trái'),
(2, 'Trai Đao', 2000, 20, 'Trái');

-- --------------------------------------------------------

--
-- Table structure for table `materialbill`
--

CREATE TABLE `materialbill` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `totalPrice` int(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `materialbilldetail`
--

CREATE TABLE `materialbilldetail` (
  `id` int(11) NOT NULL,
  `materialId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `materialBillId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderingbill`
--

CREATE TABLE `orderingbill` (
  `id` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderingbilldetail`
--

CREATE TABLE `orderingbilldetail` (
  `id` int(11) NOT NULL,
  `orderMenuId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderingBillId` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ordermenu`
--

CREATE TABLE `ordermenu` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `ordermenu`
--

INSERT INTO `ordermenu` (`id`, `name`, `price`) VALUES
(1, 'Tra Da', 1000),
(3, 'Tra Dao', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `usergroup`
--

CREATE TABLE `usergroup` (
  `groupid` int(10) UNSIGNED NOT NULL,
  `groupname` varchar(45) NOT NULL,
  `notes` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usergroup`
--

INSERT INTO `usergroup` (`groupid`, `groupname`, `notes`) VALUES
(1, 'admin', ''),
(2, 'user', ''),
(3, 'customer', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `mobilephone` varchar(45) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `firstname`, `lastname`, `sex`, `address`, `email`, `mobilephone`, `groupid`) VALUES
(1, '123456', 'Tim', 'Cook', 'Male', '123 California', 'admin@gmail.com', '987654321', 1),
(2, '123456', 'Dom', 'Linda', 'Femail', '999 Tran Hung Dao99', 'user@gmail.com', '123456789', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `materialbill`
--
ALTER TABLE `materialbill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `materialbilldetail`
--
ALTER TABLE `materialbilldetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderingbill`
--
ALTER TABLE `orderingbill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderingbilldetail`
--
ALTER TABLE `orderingbilldetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordermenu`
--
ALTER TABLE `ordermenu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `usergroup`
--
ALTER TABLE `usergroup`
  ADD PRIMARY KEY (`groupid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `materialbill`
--
ALTER TABLE `materialbill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `materialbilldetail`
--
ALTER TABLE `materialbilldetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderingbill`
--
ALTER TABLE `orderingbill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderingbilldetail`
--
ALTER TABLE `orderingbilldetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ordermenu`
--
ALTER TABLE `ordermenu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `usergroup`
--
ALTER TABLE `usergroup`
  MODIFY `groupid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=350;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
