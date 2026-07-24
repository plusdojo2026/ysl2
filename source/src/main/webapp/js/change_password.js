const new_pw = document.getElementById("new_pw");
const new_pw_confirm = document.getElementById("new_pw_confirm");
const pwdMatchMsg = document.getElementById("pwd-match-msg");

const validatePassword = () => {
  const newPwd = new_pw?.value || "";
  const confirmPwd = new_pw_confirm?.value || "";

  if (!newPwd || !confirmPwd) {
    pwdMatchMsg.textContent = "";
    pwdMatchMsg.className = "pwd-match-msg";
    return;
  }

  if (newPwd === confirmPwd) {
    pwdMatchMsg.textContent = "";
    pwdMatchMsg.className = "pwd-match-msg";
  } else {
    pwdMatchMsg.textContent = "パスワードが一致しません";
    pwdMatchMsg.className = "pwd-match-msg error";
  }
};

new_pw?.addEventListener("input", validatePassword);
new_pw_confirm?.addEventListener("input", validatePassword);
