CREATE DATABASE ysl2;
-- ユーザー作成
CREATE USER 'ysl2'@'localhost' IDENTIFIED BY 'ZwH54wxWJQt378SN';

-- 権限を付与（全DB・全テーブルへのフルアクセス）
GRANT ALL PRIVILEGES ON *.* TO 'ysl2'@'localhost';

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
    man_hours_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
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
INSERT INTO users (
    login_id,
    pw,
    name,
    mail,
    is_admin
) VALUES
('sample1',  'sample1',  '山田太郎',   'sample1@a.jp', 1),
('sample2',  'sample2',  '佐藤花子',   'sample2@a.jp', 0),
('sample3',  'sample3',  '鈴木一郎',   'sample3@a.jp', 0),
('sample4',  'sample4',  '高橋美咲',   'sample4@a.jp', 0),
('sample5',  'sample5',  '田中健太',   'sample5@a.jp', 0),
('sample6',  'sample6',  '伊藤優奈',   'sample6@a.jp', 0),
('sample7',  'sample7',  '渡辺翔太',   'sample7@a.jp', 0),
('sample8',  'sample8',  '中村葵',     'sample8@a.jp', 0),
('sample9',  'sample9',  '小林蓮',     'sample9@a.jp', 0),
('sample10', 'sample10', '加藤結衣',   'sample10@a.jp', 0);

INSERT INTO cases (
    case_id,
    case_name,
    customer_name,
    status,
    priority,
    pm_id,
    start_date,
    end_date,
    budgeted_man_hours,
    memo
) VALUES
('L001', 'ECサイトリニューアル',       '株式会社A',        '進行中', '高', 1, '2026-08-01', '2026-10-31',  480,  '案件のサンプルデータだよ'),
('L002', '販売業務システム開発',       'B株式会社',        '進行中', '低', 2, '2026-09-01', '2027-01-31', 1200,  '案件のサンプルデータその2だよ'),
('L003', '在庫管理システム改修',       '株式会社C',        '進行中', '中', 3, '2026-08-15', '2026-12-15',  800,  '既存システムの機能追加対応'),
('L004', '顧客管理システム導入',       'D商事株式会社',    '計画中', '高', 1, '2026-10-01', '2027-03-31', 1000,  'CRMパッケージ導入案件'),
('L005', '社内ポータル刷新',           '株式会社E',        '進行中', '中', 5, '2026-07-01', '2026-09-30',  360,  '社員向けポータルサイト再構築'),
('L006', '会計システムクラウド移行',   'Fホールディングス', '進行中', '高', 2, '2026-08-01', '2027-02-28', 1400, 'オンプレミス環境から移行'),
('L007', '物流管理システム構築',       '株式会社G',        '完了',   '中', 1, '2026-04-01', '2026-07-31',  960,  '予定通りリリース完了'),
('L008', '勤怠管理アプリ開発',         'H株式会社',        '進行中', '低', 7, '2026-09-15', '2026-12-31',  600,  'モバイル対応を含む'),
('L009', 'データ分析基盤構築',         '株式会社I',        '計画中', '高', 3, '2026-11-01', '2027-04-30', 1300, 'BI環境の新規整備'),
('L010', '予約管理システム運用改善',   'Jサービス株式会社', '進行中', '中', 8, '2026-08-20', '2026-11-30',  500, 'パフォーマンス改善が主目的');

INSERT INTO tasks (
    case_id,
    task_name,
    manager,
    status,
    priority,
    start_date,
    deadline_date,
    estimated_man_hours,
    task_progress,
    memo
) VALUES
('L001', '要件定義',               1, '完了',   '高', '2026-08-01', '2026-08-15',  80, 100, 'タスクのサンプルデータです。'),
('L001', '商品一覧画面開発',       2, '進行中', '中', '2026-08-16', '2026-09-30', 160,  60, 'タスクのサンプルデータです。'),
('L002', '基本設計',               1, '完了',   '低', '2026-09-01', '2026-09-30', 120, 100, 'タスクのサンプルデータです。'),
('L002', '機能開発',               3, '進行中', '低', '2026-10-01', '2026-11-30', 240,  40, 'タスクのサンプルデータです。'),
('L003', '現状調査',               4, '完了',   '中', '2026-08-15', '2026-08-31',  40, 100, '既存業務フローの調査。'),
('L003', '在庫管理機能改修',       4, '進行中', '高', '2026-09-01', '2026-11-15', 220,  45, '在庫引当処理の改善。'),
('L004', '導入計画策定',           5, '未着手', '高', '2026-10-01', '2026-10-31', 100,   0, '導入スケジュール作成。'),
('L005', '画面デザイン作成',       1, '進行中', '中', '2026-07-15', '2026-08-31', 120,  70, 'デザインガイドライン準拠。'),
('L006', 'クラウド環境構築',       2, '進行中', '高', '2026-08-01', '2026-10-15', 280,  35, 'AWS環境の構築作業。'),
('L007', 'システムテスト',         1, '完了',   '中', '2026-06-15', '2026-07-20', 160, 100, '障害なくテスト完了。');

INSERT INTO man_hours (
    task_id,
    today_man_hours,
    work_details,
    work_date,
    user_id
) VALUES
(1, 8.0, 'ミーティング・ヒアリング', '2026-08-01', 1),
(1, 7.5, '業務フロー整理',         '2026-08-04', 5),
(1, 8.0, 'システム調査',           '2026-08-05', 3),
(1, 7.5, '画面要件整理',           '2026-08-06', 7),

(2, 8.0, '商品一覧画面設計',       '2026-08-18', 4),
(2, 8.0, 'API仕様確認',           '2026-08-19', 8),
(2, 7.0, '画面実装',             '2026-08-20', 1),

(3, 8.0, '設計方針整理',         '2026-09-01', 3),
(3, 9.0, 'システム構成検討',     '2026-09-02', 6),
(3, 8.0, '画面設計',             '2026-09-03', 9),

(4, 8.0, 'テーブル設計',         '2026-10-01', 1),
(4, 8.5, 'バッチ設計',           '2026-10-02', 8),
(4, 7.5, '機能実装',             '2026-10-05', 10),

(5, 6.0, '現行業務整理',         '2026-08-15', 4),
(5, 7.5, '課題洗い出し',         '2026-08-18', 6),

(6, 8.0, '在庫機能改修',         '2026-09-10', 5),
(6, 8.0, 'レビュー対応',         '2026-09-11', 7),
(6, 7.0, '単体テスト',           '2026-09-14', 9),

(8, 8.0, 'ワイヤーフレーム作成', '2026-07-20', 1),
(8, 7.5, '画面デザイン作成',     '2026-07-21', 4),

(9, 8.0, 'AWS環境構築',          '2026-08-05', 2),
(9, 8.0, 'ネットワーク設定',      '2026-08-06', 5),
(9, 6.5, '動作確認',            '2026-08-07', 8),

(10, 8.0, '結合テスト実施',      '2026-06-20', 7),
(10, 7.0, '不具合修正確認',      '2026-06-23', 10);

-- 期限超過タスク追加
INSERT INTO tasks (
    case_id,
    task_name,
    manager,
    status,
    priority,
    start_date,
    deadline_date,
    estimated_man_hours,
    task_progress,
    memo
) VALUES
('L004', '業務要件ヒアリング', 1, '進行中', '高',
 '2026-06-01', '2026-06-30', 80, 70,
 '期限超過テスト用データ'),

('L005', '画面モックレビュー', 3, '未着手', '中',
 '2026-05-15', '2026-07-15', 40, 0,
 '期限超過テスト用データ'),

('L006', '移行計画書作成', 5, '進行中', '高',
 '2026-06-15', '2026-07-20', 120, 85,
 '期限超過テスト用データ');
