📝 NoteApp – Jetpack Compose & MVI
NoteApp là một ứng dụng ghi chú đơn giản được xây dựng bằng Jetpack Compose, áp dụng kiến trúc MVI (Model – View – Intent) với luồng dữ liệu một chiều.

🎯 Chức năng chính
⭐ Hiển thị danh sách ghi chú (Home)
⭐ Thêm ghi chú mới
⭐ Chỉnh sửa ghi chú
⭐ Xóa ghi chú
⭐ Thay đổi tiêu đề, nội dung
⭐ Thay đổi màu nền của ghi chú

🧠 Kiến trúc MVI
⭐ Ứng dụng tuân theo Unidirectional Data Flow:
⭐ UI → Intent → ViewModel → State → UI
✅ Intent: đại diện cho hành động người dùng (add, update, delete, change color…)
✅ State: trạng thái UI bất biến
✅ ViewModel: xử lý intent và cập nhật state
✅ Compose UI: render UI dựa trên state
<img width="6000" height="3375" alt="image" src="https://github.com/user-attachments/assets/f92c2898-90c1-4381-a2d1-e9228919c116" />

🎨 File UI
<img width="1132" height="791" alt="image" src="https://github.com/user-attachments/assets/49c687b7-89c6-4801-b8ed-3ea74e3202c2" />
