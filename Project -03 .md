````md
# PROJECT - 03 (Frontend)

## Project ko responsive kaise banaya hai ?

```html
<meta name="viewport" content="width=device-width, initial-scale=1">
````

`1` is liye diya hai takki wo 100% zoom ho.

---

# BOOTSTRAP

## Bootstrap kai liye 3 library add ki hai -> header.jsp

```html
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script
src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script
src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
```

---

# INDEX PAGE

## Background image kai liye kya kiya ?

```html
<style>
body {
    background-image: url('img/onlineresultsys.jpg');
    background-size: cover;
    background-repeat: no-repeat;
}
</style>
```

---

# HEADER (NAVBAR)

## Header (Navbar) kai liye kya kiya hai?

```html
<div class="header">
    <nav class="navbar navbar-expand-lg fixed-top aj">
</div>
```

---

## Header par color change kyu aa raha hai OR color alag-alag kyu aa raha hai?

```html
<style type="text/css">
.aj {
    background-image: linear-gradient(to bottom right, grey, black);
}
</style>
```

---

## Image ko responsive kaise banaya hai ?

```html
<a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>">
    <img src="<%=ORSView.APP_CONTEXT%>/img/NCCHOPRA.png"
    width="190px"
    height="50px">
</a>
```

---

## Hello guest left mai kaise aa raha hai ?

```html
<ul class="nav navbar-nav ml-auto">
```

* `ul` -> unordered list
* `ml` -> margin left

---

## Dropdown kaise aa raha hai ?

(Dropdown wale symbol ko carrot bolte hai)

Iske liye humne Bootstrap ki 4 classes ka use kiya hai:

1. `dropdown` (list tag mai diya hai)
2. `dropdown-toggle` (anchor tag kai class attribute mai)
3. `dropdown-menu` (div tag box mai)
4. `dropdown-item` (anchor tag mai diya hai)

Link humne dropdown-item mai di hai.

---

# WELCOME

## Welcome message ki styling humne kaise ki hai ?

```html
<div class="text-cs1">
```

---

# LOGIN

## Login box bich mai kaise aa raha hai?

Bootstrap 12 grid system par kaam karta hai.

Iske liye humne `col-md-4` ka use kiya hai aur 4-4 grid ko merge kiya hai. Hume bich wale `col-md-4` mai coding ki hai.

---

## Box card kaise aa raha hai ?

Iske liye humne login par `card` aur `card-body` ka use kiya hai class attribute mai.

---

## Box kai niche shadow kaise aa rahi hai ?

```html
<div class="card input-group-addon grad">
```

Style tag mai internal CSS ka call kiya hai.

* `input-group-addon` -> shadow
* `card grad` -> box

---

## Input field ek line mai kyu aa raha hai ?

```html
<div class="input-group-text">
```

---

## Field aur Icon merge ho kar kaise aa rahe hai ?

```html
<div class="input-group">
    <div class="input-group-prepend">
```

Ka use kiya hai. (Dono alag div tag mai hai)

---

## Field sai shadow kaise hat rahi click karne par ?

Iske liye humne input tag kai andar class attribute mai `"form-control"` ka use kiya hai.

* `input-group-addon` -> shadow ko laa rahi hai

---

## Email Id aur password ek line kaise aa rahe hai ?

```html
<span class="pl-sm-5">
```

Ka use kiya hai.

---

## Icon kai age space kaise aa rahi hai ?

```html
<div class="col-sm-12">
```

* `col` -> column
* `sm` -> small margin

---

## User List par table hover (highlight) kai liye kya kiya hai ?

```html
<table class="table table-bordered table-dark table-hover">
```

Ka use kiya hai.

---

# Full Form of fa, fas, far

* `fa` -> Font Awesome
  (Ek `fa` likhne sai item load hota hai)

* `fas` -> Font Awesome Solid
  (Solid icons kai liye use hota hai, ye thoda bold dikhta hai)

* `far` -> Font Awesome Regular
  (Regular style icon kai liye thoda lightweight ya outline style hota hai)

---

## pb

* `pb` -> padding bottom

```

Source: :contentReference[oaicite:0]{index=0}
```

