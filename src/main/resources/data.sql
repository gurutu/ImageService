DROP TABLE IF EXISTS imageInfo;
 
CREATE TABLE imageInfo (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  image_name VARCHAR(250) NOT NULL,
  hmac VARCHAR(250) NOT NULL
);
 
INSERT INTO imageInfo (image_name, hmac) VALUES
  ('Aliko', 'Dangote');