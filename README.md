<h1>📝 NoteApp – Jetpack Compose & MVI</h1>

<p>
NoteApp là một ứng dụng ghi chú đơn giản được xây dựng bằng <b>Jetpack Compose</b>,
áp dụng kiến trúc <b>MVI (Model – View – Intent)</b> với luồng dữ liệu một chiều.
</p>

<hr/>

<h2>🎯 Chức năng chính</h2>
<ul>
  <li>⭐ Hiển thị danh sách ghi chú (Home)</li>
  <li>⭐ Thêm ghi chú mới</li>
  <li>⭐ Chỉnh sửa ghi chú</li>
  <li>⭐ Xóa ghi chú</li>
  <li>⭐ Thay đổi tiêu đề, nội dung</li>
  <li>⭐ Thay đổi màu nền của ghi chú</li>
</ul>

<hr/>

<h2>🧠 Kiến trúc MVI</h2>

<p>Ứng dụng tuân theo <b>Unidirectional Data Flow</b>:</p>

<pre><code>UI → Intent → ViewModel → State → UI</code></pre>

<ul>
  <li>✅ <b>Intent</b>: đại diện cho hành động người dùng (add, update, delete, change color…)</li>
  <li>✅ <b>State</b>: trạng thái UI bất biến</li>
  <li>✅ <b>ViewModel</b>: xử lý intent và cập nhật state</li>
  <li>✅ <b>Compose UI</b>: render UI dựa trên state</li>
</ul>

<img 
  src="https://github.com/user-attachments/assets/f92c2898-90c1-4381-a2d1-e9228919c116"
  alt="MVI Flow Diagram"
  width="100%"
/>

<hr/>

<h2>🎨 File UI</h2>

<img 
  src="https://github.com/user-attachments/assets/49c687b7-89c6-4801-b8ed-3ea74e3202c2"
  alt="UI Design"
  width="100%"
/>
****
