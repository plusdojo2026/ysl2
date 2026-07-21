-- DAOで使うであろうSQL文をいくつか用意しました。参考にしてね★

-- 実績工数を計算するメソッド
SELECT tasks.case_id AS '案件コード', cases.case_name AS '案件名', SUM(today_man_hours) AS '実績工数'
	FROM tasks
    JOIN man_hours
    ON tasks.task_id = man_hours.task_id
    JOIN cases
    ON tasks.case_id = cases.case_id
    GROUP BY tasks.case_id;

-- タスクごとの実績工数を計算するメソッド
SELECT tasks.task_id, task_name , SUM(today_man_hours) AS '実績工数'
	FROM tasks
    JOIN man_hours
    ON tasks.task_id = man_hours.task_id
    GROUP BY tasks.task_id;
    
-- 案件ごとのタスク進捗を計算するメソッド
SELECT case_id AS '案件コード',
	COUNT(task_id) AS '総タスク数',
    COUNT(CASE WHEN status='完了' THEN 1 ELSE NULL END) AS '完了タスク数'
	FROM tasks
    GROUP BY case_id;

-- ログイン
SELECT * FROM users WHERE login_id=? and pw=?;

-- ユーザー全検索・アクティブユーザー検索
SELECT * FROM users WHERE is_active=?;  -- アクティブ検索のときは?=1

-- ユーザー登録
INSERT INTO users
    (login_id, pw, name, mail, is_admin)
    VALUES
    (?,?,?,?,?);

-- ユーザー編集・パスワード変更
UPDATE users SET
    name=?,
    mail=?,
    is_admin=?,
    is_active=?
    pw=?;

-- ログインIDの重複チェック
    SELECT user_id FROM users WHERE login_id=?;

-- 案件全検索
SELECT case_id, case_name, customer_name, users.name, start_date, end_date,
    FROM cases
    JOIN users
    ON user_id = pm_id;