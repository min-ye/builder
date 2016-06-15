CREATE TABLE `lego`.`Set` (
   `Number` VARCHAR(16) NULL,
   `Variant` SMALLINT NULL,
   `SubThemeKey` VARCHAR(36) NULL,
   `Year` SMALLINT NULL,
   `Name` VARCHAR(128) NULL,
   `Minifigs` SMALLINT NULL,
   `Piece` SMALLINT NULL,
   `PriceUK` FLOAT NULL,
   `PriceUS` FLOAT NULL,
   `PriceCA` FLOAT NULL,
   `PriceEU` FLOAT NULL,
   `ImageURL` VARCHAR(256) NULL,
   `Key` VARCHAR(36) NOT NULL,

   PRIMARY KEY (`Key`));