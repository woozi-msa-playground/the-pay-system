CREATE TABLE `membership.membership`
(
    `is_corp`       bit(1) NOT NULL,
    `is_valid`      bit(1) NOT NULL,
    `membership_id` bigint NOT NULL AUTO_INCREMENT,
    `address`       varchar(255) DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `name`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`membership_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;