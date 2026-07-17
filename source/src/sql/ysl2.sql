-- データベース作成
CREATE DATABASE ysl2;

-- ユーザー作成
CREATE USER 'ysl1'@'localhost' IDENTIFIED BY 'ZwH54wxWJQt378SN';

-- 権限を付与（全DB・全テーブルへのフルアクセス）
GRANT ALL PRIVILEGES ON *.* TO 'ysl1'@'localhost';

-- 反映
FLUSH PRIVILEGES;

USE ysl2;

-- CREATE文
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    login_id VARCHAR(100) NOT NULL UNIQUE,
    pw VARCHAR(100) NOT NULL ,
    name VARCHAR(100) NOT NULL,
    mail VARCHAR(255) ,
    is_admin INT NOT NULL DEFAULT 1,
    is_active INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cases (
    case_id VARCHAR(20) PRIMARY KEY,
    case_name VARCHAR(100) NOT NULL,
    customer_name VARCHAR(100),
    status VARCHAR(100) NOT NULL DEFAULT '進行中',
    priority VARCHAR(100) NOT NULL DEFAULT '中',
    pm_id INT NOT NULL,
    FOREIGN KEY (pm_id) REFERENCES users(user_id),
    start_date DATE,
    end_date DATE,
    budgeted_man_hours DOUBLE,
    memo TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    case_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (case_id) REFERENCES cases(case_id),
    task_name VARCHAR(100) NOT NULL, 
    manager INT,
    FOREIGN KEY (manager) REFERENCES users(user_id),
    status VARCHAR(100) NOT NULL DEFAULT '未着手',
    priority VARCHAR(100) NOT NULL DEFAULT '中',
    start_date DATE,
    deadline_date DATE,
    estimated_man_hours DOUBLE,
    task_progress INT NOT NULL DEFAULT 0,
    memo TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE man_hours (
    mon_hours_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    task_id INT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES tasks(task_id) ON DELETE CASCADE,
    today_man_hours DOUBLE NOT NULL,
    work_details VARCHAR(255),
    work_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- サンプルデータ INSERT文
-- ユーザー登録
INSERT INTO users
    (login_id, pw, name, mail, is_admin)
    VALUES
    ('sample1', 'sample1', 'sample1', 'sample1@a.jp', 1),   -- 管理者
    ('sample2', 'sample2', 'sample2', 'sample2@a.jp', 0),   -- 一般ユーザー
    ('sample3', 'sample3', 'sample3', 'sample3@a.jp', 0);

-- 案件登録
INSERT INTO cases
    (case_id, case_name, customer_name, status, priority, pm_id, start_date, end_date, budgeted_man_hours, memo)
    VALUES
    ('L001', 'ECサイトリニューアル', '株式会社A', '進行中', '高', '2', '2026-08-01', '2026-10-31', 480, '案件のサンプルデータだよ'),
    ('L002', '販売業務システム開発', 'B株式会社', '進行中', '低', '2', '2026-09-01', '2027-01-31', 1200, '案件のサンプルデータその2だよ');

-- タスク登録
INSERT INTO tasks
    (case_id, task_name, manager, status, priority, start_date, deadline_date, estimated_man_hours, task_progress, memo)
    VALUES
    ('L001', '要件定義', 2, '完了', '高', '2026-08-01', '2026-08-15', 80, 20, 'タスクのサンプルデータです。'),
    ('L001', '商品一覧画面開発', 2, '進行中', '中', '2026-08-16', '2026-09-30', 160, 0, 'タスクのサンプルデータです。'),
    ('L002', '基本設計', 3, '完了', '低', '2026-09-01', '2026-09-30', 120, 0, 'タスクのサンプルデータです。'),
    ('L002', '機能開発', 3, '進行中', '低', '2026-10-01', '2026-11-30', 240, 0, 'タスクのサンプルデータです。');

-- 工数登録
INSERT INTO man_hours
    (task_id, today_man_hours, work_details, work_date, user_id)
    VALUES
    (2, 8, 'ミーティング・ヒアリング', '2026-08-01', 2),
    (2, 7.5, '業務フロー整理', '2026-08-04', 2),
    (2, 8, 'システム調査', '2026-08-05', 2),
    (2, 7.5, '画面要件整理', '2026-08-06', 2),
    (3, 8, '設計方針整理', '2026-09-01', 3),
    (3, 9, 'システム構成検討', '2026-09-02', 3),
    (3, 8, '画面設計', '2026-09-03', 3);