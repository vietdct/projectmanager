CREATE DATABASE ProjectManager
Use ProjectManager;

-- 1) ROLE
CREATE TABLE role (
  id           INT AUTO_INCREMENT  ,
  name         VARCHAR(100) NOT NULL UNIQUE,
  description  VARCHAR(255)
  PRIMARY KEY(id)
);
INSERT INTO role (name, description) VALUES ('DEV', 'Lập trình viên / Developer');
UPDATE role SET description = 'Quản trị hệ thống'     WHERE name = 'ADMIN';
UPDATE role SET description = 'Người dùng thông thường' WHERE name = 'USER';
UPDATE role SET description = 'Quản lý dự án'         WHERE name = 'MANAGER';


-- 2) STATUS
CREATE TABLE status (
  id    INT AUTO_INCREMENT ,
  name  VARCHAR(100) NOT NULL UNIQUE,
  
  PRIMARY KEY(id)
)

-- 3) PROJECT
CREATE TABLE 	project  (
  id          INT AUTO_INCREMENT,
  name        VARCHAR(200) NOT NULL,
  start_date  DATE,
  end_date    DATE,
  
  PRIMARY KEY(id),
  CONSTRAINT chk_project_dates CHECK (end_date IS NULL OR start_date IS NULL OR end_date >= start_date)
);

-- 4) USER
CREATE TABLE user (
  id         INT AUTO_INCREMENT ,
  username   VARCHAR(200) NOT NULL UNIQUE,
  email      VARCHAR(200) NOT NULL UNIQUE,
  password   VARCHAR(255) NOT NULL,
  phone      VARCHAR(30),
  first_name VARCHAR(100),
  last_name  VARCHAR(100),
  id_role    INT,
  
  PRIMARY KEY(id)
) ;
  ALTER TABLE user ADD CONSTRAINT FK_id_role_user FOREIGN KEY(id_role) REFERENCES role(id);
  
  
-- 5) TASK
CREATE TABLE task (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(200) NOT NULL,
  start_date  DATE,
  end_date    DATE,
  id_project  INT ,
  id_status   INT,                         -- trạng thái tổng quát của task (tuỳ chọn)

  CONSTRAINT chk_task_dates CHECK (end_date IS NULL OR start_date IS NULL OR end_date >= start_date)
) ;

ALTER TABLE task ADD CONSTRAINT FK_id_project_task FOREIGN KEY(id_project) REFERENCES project(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE task ADD CONSTRAINT FK_id_status_task FOREIGN KEY(id_status) REFERENCES status(id) ON UPDATE CASCADE ON DELETE SET NULL;

-- 6) ASSIGN_TASK (bảng giao việc nhiều-nhiều giữa User và Task)
CREATE TABLE assign_task (
  id_user   INT NOT NULL,
  id_task   INT NOT NULL,
  id_status INT,                           -- trạng thái của việc giao cho user cụ thể (nếu dùng)
  
  PRIMARY KEY (id_user, id_task)

) ;
ALTER TABLE assign_task ADD CONSTRAINT FK_id_task_assign_task FOREIGN KEY(id_task) REFERENCES task(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE assign_task ADD CONSTRAINT FK_id_task_status_task FOREIGN KEY(id_status) REFERENCES status(id) ON UPDATE CASCADE ON DELETE SET NULL ;
ALTER TABLE assign_task ADD CONSTRAINT FK_id_user_task FOREIGN KEY(id_user) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE ;
-- === Gợi ý dữ liệu mẫu (tùy chọn) ===
INSERT INTO role(name) VALUES 
('ADMIN'),
('USER'),
('DEV');

INSERT INTO status(name) VALUES
('NOT_STARTED'),('IN_PROGRESS'),('COMPLETED');

INSERT INTO project(name, start_date) VALUES
('CRM App', CURDATE()),
('QLSV', CURDATE());

-- Người dùng mẫu
INSERT INTO user(username , email, password, id_role, phone, first_name, last_name) VALUES
('thanhquang','thanhquang1996@gmail.com', '$2a$10$XjuSbY7RP5ZQD3fhCrNJbelrH6tddiVGy/8OTGFOyqjA9BKDBfgbC	', 1, 09057726778, 'Tran Nguyen', 'Thanh Quang'),
('user','user@gmail.com',    '$2a$10$O5sdCf.w.TCoilwSwTGXvum.WcaRJcVaKAdnyDQ8iUc8cv.4bdjXW', 2, 0989397134, 'Thi', 'Hat'),
('dev01','dev@example.com',  '$2a$10$MIKBZ/H89GqHMZZpGVvD1OBmpqspZH76hVaQ9fgepxUKFgJNBUMYy',  3, 0923126197, 'Thanh', 'Quang');
 

-- Task mẫu
INSERT INTO task(name, id_project, id_status) VALUES
('Thiết kế DB', 1, 2),
('Xây API',     1, 1),
('UI mockup',   2, 1);

-- Phân công
INSERT INTO assign_task(id_user, id_task, id_status) VALUES
(130, 1, 2),   -- PM theo dõi "Thiết kế DB"
(98, 1, 2),   -- DEV làm "Thiết kế DB"
(129, 2, 1);   -- DEV làm "Xây API"

SELECT u.id ,u.first_name ,u.last_name , u.email , r.name 
FROM user u 
JOIN role r ON u.id_role  = r.id ; 

SELECT r.id ,r.name  , COUNT(r.id )
FROM role r 
JOIN user u on r.id  = u.id_role 
GROUP BY r.name
HAVING r.id =1;

