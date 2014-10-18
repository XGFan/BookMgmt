// JavaScript Document
//View Code 

  $(function () {

            $.post("college_action", null, function (data) {

                var total = data;
                PageClick(1, total, 3);
            });

            PageClick = function (pageIndex, total, spanInterval) {
                $.ajax({
                    url: "college_action",
                    data: { "PageIndex": pageIndex },
                    type: "post",
                    dataType: "json",
                    success: function (data) {

                        //�����1��ʼ
                        //����ǰҳ����תΪint����
                        var intPageIndex = parseInt(pageIndex);

                        //��ȡ��ʾ��ݵı��
                        var table = $("#content");
                        //������������
                        $("#content tr").remove();

                        //�������������
                        for (var i = 0; i < data.length; i++) {
                            table.append(
                                $("<tr><td>" +
                                data[i].Title
                                + "</td><td>" +
                                data[i].Auhor
                                + "</td><td>" +
                                data[i].PublishDate
                                + "</td><td>" +
                                data[i].ISBN
                                + "</td></tr>")
                                );
                        } 

                        //������ҳ
                        //���ܼ�¼���� �õ� ��ҳ����
                        var pageS = total
                        if (pageS % 10 == 0) pageS = pageS / 10;
                        else pageS = parseInt(total / 10) + 1;
                        var $pager = $("#pager");

                        //�����ҳdiv�е�����
                        $("#pager span").remove();
                        $("#pager a").remove();

                        //��ӵ�һҳ
                        if (intPageIndex == 1)
                            $pager.append("<span class='disabled'>��һҳ</span>");
                        else {
                            var first = $("<a href='javascript:void(0)' first='" + 1 + "'>��һҳ</a>").click(function () {
                                PageClick($(this).attr('first'), total, spanInterval);
                                return false;
                            });
                            $pager.append(first);
                        }


                        //�����һҳ
                        if (intPageIndex == 1)
                            $pager.append("<span class='disabled'>��һҳ</span>");
                        else {
                            var pre = $("<a href='javascript:void(0)' pre='" + (intPageIndex - 1) + "'>��һҳ</a>").click(function () {
                                PageClick($(this).attr('pre'), total, spanInterval);
                                return false;
                            });
                            $pager.append(pre);
                        }

                        //���÷�ҳ�ĸ�ʽ  ������Ը����������Լ���Ҫ�Ľ��
                        var interval = parseInt(spanInterval); //���ü��
                        var start = Math.max(1, intPageIndex - interval); //������ʼҳ
                        var end = Math.min(intPageIndex + interval, pageS)//����ĩҳ

                        if (intPageIndex < interval + 1) {
                            end = (2 * interval + 1) > pageS ? pageS : (2 * interval + 1);
                        }

                        if ((intPageIndex + interval) > pageS) {
                            start = (pageS - 2 * interval) < 1 ? 1 : (pageS - 2 * interval);

                        }

                        //���ҳ��
                        for (var j = start; j < end + 1; j++) {
                            if (j == intPageIndex) {
                                var spanSelectd = $("<span class='current'>" + j + "</span>");
                                $pager.append(spanSelectd);
                            } //if 
                            else {
                                var a = $("<a href='javascript:void(0)'>" + j + "</a>").click(function () {
                                    PageClick($(this).text(), total, spanInterval);
                                    return false;
                                });
                                $pager.append(a);
                            } //else
                        } //for

                        //��һҳ
                        if (intPageIndex == total) {
                            $pager.append("<span class='disabled'>��һҳ</span>");

                        }
                        else {

                            var next = $("<a href='javascript:void(0)' next='" + (intPageIndex + 1) + "'>��һҳ</a>").click(function () {
                                PageClick($(this).attr("next"), total, spanInterval);
                                return false;
                            });
                            $pager.append(next);
                        }

                        //���һҳ
                        if (intPageIndex == pageS) {
                            $pager.append("<span class='disabled'>���һҳ</span>");

                        }
                        else {
                            var last = $("<a href='javascript:void(0)' last='" + pageS + "'>���һҳ</a>").click(function () {
                                PageClick($(this).attr("last"), total, spanInterval);
                                return false;
                            });
                            $pager.append(last);
                        }

                    } //sucess

                }); //ajax

            }; //function

        }); // ready
