<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
      
      #home{
        width: 40px;
        height: 40px;
      }


      .carousel-nav {
        position: absolute;
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
        z-index: 10;
      }

      .carousel-tab {
        display: flex;
        flex-direction: column;
        align-items: center;
        cursor: pointer;
      }

      .carousel-tab .label {
        font-weight: bold;
        color: black;
      }

      .carousel-tab .bar {
        width: 70px;
        height: 1.5px;
        background-color: #ccc;
        margin-top: 5px;
        border-radius: 3px;
        transition: background-color 0.3s ease;
      }
      .carousel-tab .label.active + .bar {
        background-color: black;
      }

      /* 드롭박스 설정정 */
      .dropdown-menu-box {
        display: none;
        position: absolute;
        width: 700px;
        height: 140px;
        top: 68.5px;
        left: 50%;
        transform: translateX(-50%);
        background-color: rgba(234, 233, 233, 0.908);
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        z-index: 20;
      }
      .dropdown-content {
        display: flex;
        gap: 40px;
      }
      .dropdown-content div {
        text-align: center;
      }

      .item-img {
        width: 0px;
        height: auto;
        margin-bottom: 5px;
      }

      .dropdown-menu-box.show {
        display: block;
      }

		/* 드롭박스 내부 표 설정 */
		.table-box {
		  border-radius: 10px;
		  background-color: rgba(255, 255, 255, 0.9);
		  overflow: hidden;
		  border-collapse: separate;
		  width: 100%;
		  table-layout: fixed;  /* 👈 열 너비를 동일하게 만들기 위해 추가 */
		}
		.table-box1 {
		  border-radius: 10px;
		  background-color: rgba(255, 255, 255, 0.9);
		  overflow: hidden;
		  border-collapse: separate;
		  width: 100%;
		 
		}
		
		/* 표 내부 셀 배경색 */
		.table-box th,
		.table-box td {
		  background-color: rgba(255, 255, 255, 0.9) !important;
		  text-align: center;
		  overflow: hidden;
		  text-overflow: ellipsis;
		  white-space: nowrap;
		}
		
		/* 헤더 셀 */
		.table-box thead th {
		  background-color: rgba(240, 240, 240, 0.9) !important;
		}
		
		/* 바디 셀 */
		.table-box tbody td {
		  background-color: rgba(240, 240, 240, 0.9) !important;
		}

      .dropdown-menu-box a {
        text-decoration-line: none;
        text-decoration-style: none;
        color: #2c3e50 !important;
        font-weight: bold;
        font-size: 16px;
      }


    </style>
  </head>
  <body>
 
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
          <img id="home" src="resources/images/1uniqlo.png" alt="Home">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" class="menu" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="log-in">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="signup-form">Signup</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="log-out">Logout</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="my-page">Mypage</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>


    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" style="position: relative;">

      <!--  -->
      <div class="carousel-nav d-flex justify-content-center gap-4 mt-3">
 
        <div class="carousel-tab" data-dropdown-id="dropdown-new">
          <div class="label active">NEW</div>
          <div class="bar"></div>
        </div>
        <div class="carousel-tab" data-dropdown-id="dropdown-women">
          <div class="label">WOMEN</div>
          <div class="bar"></div>
        </div>
        <div class="carousel-tab" data-dropdown-id="dropdown-men">
          <div class="label">MEN</div>
          <div class="bar"></div>
        </div>
        <div class="carousel-tab" data-dropdown-id="dropdown-kids">
          <div class="label">KIDS</div>
          <div class="bar"></div>
        </div>

      </div>

      <!-- 드롭다운 메뉴 -->
      <div class="dropdown-menu-box" id="dropdown-new">
        <div class="dropdown-content">
          <table class="table table-borderless table-box1">
            <thead>
              <tr>
                <th scope="col" ><a href="#">2025 S/S Season New Collection</a></th>
              </tr>
            </thead>
          </table>
       </div>
      </div>

      <div class="dropdown-menu-box" id="dropdown-women">
        <div class="dropdown-content">
            <table class="table table-borderless table-box">
              <tbody>
                <tr>
                  <td scope="col" ><a href="#">Outer</a></td>
                  <td scope="col" ><a href="#">T-shirts&Sweat</a></td>
                  <td scope="col" ><a href="#">Shirt&Blouse</a></td>
                </tr>
                <tr>
                  <td scope="col" ><a href="#">Knitwear&Cardigan</a></td>
                  <td scope="col" ><a href="#">Innerwear</a></td>
                  <td scope="col" ><a href="#">Pants</a></td>
                </tr>
              </tbody>
            </table>
       </div>
      </div>

      <div class="dropdown-menu-box" id="dropdown-men">
        <div class="dropdown-content">
          <table class="table table-borderless table-box">
            <tbody>
              <tr>
                <td scope="col" ><a href="#">Outer</a></td>
                <td scope="col" ><a href="#">T-shirts&Sweat</a></td>
                <td scope="col" ><a href="#">Shirt&Blouse</a></td>
              </tr>
              <tr>
                <td scope="col" ><a href="#">Knitwear&Cardigan</a></td>
                <td scope="col" ><a href="#">Innerwear</a></td>
                <td scope="col" ><a href="#">Pants</a></td>
              </tr>
            </tbody>
          </table>
       </div>
      </div>
      <div class="dropdown-menu-box" id="dropdown-kids">
        <div class="dropdown-content">
          <table class="table table-borderless table-box">
            <tbody>
              <tr>
                <td scope="col" ><a href="#">Outer</a></td>
                <td scope="col" ><a href="#">T-shirts&Sweat</a></td>
                <td scope="col" ><a href="#">Innerwear</a></td>
                <td scope="col" ><a href="#">Pants</a></td>
              </tr>
            </tbody>
          </table>
       </div>
      </div>

      <!-- 배경 사진들들 -->
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img  src="resources/images/uniklo.jpg" class="d-block w-100" alt="tt" style="opacity: 0.80;">
        </div>
        <div class="carousel-item">
          <img src="resources/images/women.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="resources/images/men.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="resources/images/kids.jpg" class="d-block w-100" alt="...">
        </div>
      </div>
      <!-- 화면 이동 버튼 -->
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>

    </div>




    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

    
    <script>
      document.addEventListener("DOMContentLoaded", () => {
        const carousel = bootstrap.Carousel.getOrCreateInstance(document.querySelector('#carouselExampleCaptions'));
    
        document.querySelectorAll('.carousel-tab').forEach((tab, index) => {
          const dropdownId = tab.dataset.dropdownId;
    
          // 마우스를 올렸을 때 동작
          tab.addEventListener('mouseenter', () => {
            carousel.to(index);
            updateActiveTab(index);
            showDropdown(dropdownId);
          });
    
          // 클릭 시에도 동작
          tab.addEventListener('click', () => {
            carousel.to(index);
            updateActiveTab(index);
            showDropdown(dropdownId);
          });
        });
    
        // 슬라이드 자동 전환 시 탭 상태 업데이트
        document.querySelector('#carouselExampleCaptions').addEventListener('slid.bs.carousel', function (e) {
          updateActiveTab(e.to);
        });
    
        // 바깥 클릭 시 드롭다운 닫기
        window.addEventListener('click', function (e) {
          if (!e.target.closest('.carousel-tab') && !e.target.closest('.dropdown-menu-box')) {
            document.querySelectorAll('.dropdown-menu-box').forEach(box => box.classList.remove('show'));
          }
        });
      });
    
      function updateActiveTab(index) {
        document.querySelectorAll('.carousel-tab .label').forEach(label => {
          label.classList.remove('active');
        });
        document.querySelectorAll('.carousel-tab .label')[index].classList.add('active');
    
        document.querySelectorAll('.carousel-tab .bar').forEach(bar => {
          bar.style.backgroundColor = '#ccc';
        });
        document.querySelectorAll('.carousel-tab .bar')[index].style.backgroundColor = 'black';
      }
    
      function showDropdown(id) {
        document.querySelectorAll('.dropdown-menu-box').forEach(drop => {
          drop.classList.remove('show');
        });
    
        const target = document.getElementById(id);
        if (target) {
          target.classList.add('show');
        }
      }

      // 드롭다운 외의 영역에 마우스를 올리면 드롭다운 사라지게
      document.addEventListener('mousemove', (e) => {
        const nav = document.querySelector('.carousel-nav');
        const dropdowns = document.querySelectorAll('.dropdown-menu-box');
        const isInsideNav = nav.contains(e.target);
        const isInsideDropdown = Array.from(dropdowns).some(dropdown => dropdown.contains(e.target));

        if (!isInsideNav && !isInsideDropdown) {
          dropdowns.forEach(drop => drop.classList.remove('show'));
        }
      });
    </script>
  </body>
</html>