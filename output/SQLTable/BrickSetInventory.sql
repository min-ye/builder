CREATE TABLE `lego`.`Inventory` (
   `SetNumber` VARCHAR(16) NOT NULL,
   `PartID` VARCHAR(16) NOT NULL,
   `Quantity` VARCHAR(8) NULL,
   `Colour` VARCHAR(128) NULL,
   `Category` VARCHAR(128) NULL,
   `DesignID` VARCHAR(16) NULL,
   `PartName` VARCHAR(128) NULL,
   `ImageURL` VARCHAR(256) NULL,
   `SetCount` VARCHAR(8) NULL,

   PRIMARY KEY (`SetNumber`,`PartID`));