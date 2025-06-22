"use strict";

$(() => {
  const stars = $("[class=star]");
  const hiddenInput = $("#rate");
  let selectedValue = 0;

  // 星の表示を変えるための関数
  const highlightStars = (count) => {
    stars.each(function () {
      const val = parseInt($(this).data("value"), 10);
      if (val <= count) {
        $(this).text("★");
      } else {
        $(this).text("☆");
      }
    });
  };

  const initValue = parseInt(hiddenInput.val() || 0, 10);
  if (initValue) {
    selectedValue = initValue;
    highlightStars(selectedValue);
    stars.each(function () {
      const val = parseInt($(this).data("value"), 10);
      $(this).toggleClass("selected", val <= selectedValue);
    });
  }

  stars.each(function () {
    $(this).hover(
      // マウスホバー時の星表示
      () => {
        const hoveredValue = parseInt($(this).data("value"), 10);
        highlightStars(hoveredValue);

        stars.each(function () {
          const val = parseInt($(this).data("value"), 10);
          $(this).toggleClass("hovered", val <= hoveredValue);
        });
      },
      // ホバー外した時に元に戻す
      () => {
        stars.each(function () {
          $(this).removeClass("hovered");
        });
        highlightStars(selectedValue);
      }
    );

    // クリックで評価を選択
    $(this).on("click", () => {
      selectedValue = parseInt($(this).data("value"), 10);
      hiddenInput.val(selectedValue);
      highlightStars(selectedValue);

      stars.each(function () {
        const val = parseInt($(this).data("value"), 10);
        $(this).toggleClass("selected", val <= selectedValue);
      });
    });
  });
});
