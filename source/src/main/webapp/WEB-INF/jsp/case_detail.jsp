<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>案件詳細</title>
  </head>
  <body>
    <h1>案件詳細</h1>
    <input type="hidden" name="page_id" value="L003" />
    <div class="case_detail">
      <table>
        <h3>案件詳細</h3>
        <tr>
          <td>案件コード</td>
          <td>${case_id}</td>
          <td>優先度</td>
          <td>${priority}</td>
        </tr>

        <tr>
          <td>名称</td>
          <td>${case_name}</td>
          <td>期間</td>
          <td>${start_date}~${end_date}</td>
        </tr>
        <tr>
          <td>顧客名</td>
          <td>${customer_name}</td>
          <td>予算</td>
          <td>${budget_man_hours}</td>
        </tr>
        <tr>
          <td>担当PM</td>
          <td>${name}</td>
          <td>実績工数</td>
          <td>${actual_man_hours}</td>
        </tr>
        <tr>
          <td>説明</td>
          <td>${memo}</td>
          <td>進捗バー</td>
          <td>${task_progress}</td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td>ステータス</td>
          <td>${status}</td>
        </tr>
      </table>
      <input type="button" name="edit_button" value="編集" />
      <input type="submit" name="button_id" value="完了" />
      <input type="submit" name="button_id" value="中止" />
    </div>

    <div class="case_task_list">
      <table>
        <h3>案件タスク一覧</h3>
        <tr>
          <th>タスク名</th>
          <th>担当者</th>
          <th>ステータス</th>
          <th>優先度</th>
          <th>期限</th>
          <th>見積</th>
          <th>実績工数</th>
          <th>進捗バー</th>
        </tr>
        <c:forEach var="e" items="${taskList}">
          <tr>
            <th>${e.task_name}</th>
            <th>${e.manager}</th>
            <th>${e.status}</th>
            <th>${e.priority}</th>
            <th>${e.deadline_date}</th>
            <th>${e.estimated_man_hours}</th>
            <th>${e.actual_man_hours}</th>
            <th>${e.task_progress}</th>
          </tr>
        </c:forEach>
      </table>
      <input type="button" name="add_button" value="+タスク追加" />
    </div>

    <div class="manhour_log">
      <h3>工数ログ(最新10件)</h3>
      <table>
        <c:forEach var="e" items="${manList}">
          <tr>
            <th>${e.work_date}</th>
            <th>${e.task_name}</th>
            <th>${e.manager}</th>
            <th>${e.today_man_hours}</th>
            <th>${e.work_detail}</th>
          </tr>
        </c:forEach>
      </table>
    </div>

    <div>
      <h2>タスク登録</h2>
      <form method="POST" action="<c:url value='/Controller'/>">
        <input type="hidden" name="page_id" value="L003" />
        <p>
          <label for="case_name">案件名*<br /></label>
          <select name="case_name" id="case_name">
            <c:forEach var="c" items="">
              <option value="${c.case_id}">${c.case_name}</option>
            </c:forEach>
          </select>
        </p>
        <p>
          <label
            >タスク名*<br />
            <input type="text" name="task_name" />
          </label>
        </p>
        <p>
          <label for="manager">担当者<br /></label>
          <select name="manager" id="manager">
            <c:forEach var="m" items="">
              <option value="${m.user_id}">${c.user_name}</option>
            </c:forEach>
          </select>
        </p>
        <p>
          <label
            >進捗率<br />
            <input
              type="number"
              min="0"
              max="100"
              step="1"
              name="task_progress"
            />
          </label>
        </p>
        <p>
          <label
            >開始日<br />
            <input type="date" name="start_date" />
          </label>
        </p>
        <p>
          <label
            >期限<br />
            <input type="date" name="deadline_date" />
          </label>
        </p>
        <p>
          <label for="priority">優先度*<br /></label>
          <select name="priority" id="priority">
            <option value="高">高</option>
            <option value="中" selected>中</option>
            <option value="低">低</option>
          </select>
        </p>
        <p>
          <label
            >見積工数<br />
            <input
              type="number"
              min="0"
              max="24"
              step="0.5"
              name="estimated_mon_hours"
            />
          </label>
        </p>
        <p>
          <label for="status">ステータス*<br /></label>
          <select name="status">
            <option value="未着手" selected>未着手</option>
            <option value="進行中">進行中</option>
            <option value="完了">完了</option>
            <option value="保留">保留</option>
          </select>
        </p>
        <input type="submit" name="button_id" value="保存" />
        <input type="button" value="戻る" />
      </form>
    </div>
  </body>
</html>
