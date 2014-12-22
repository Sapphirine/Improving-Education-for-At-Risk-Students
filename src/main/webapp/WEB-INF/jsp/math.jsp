
<!doctype html>
<title>Linking to Data Table</title>
<link rel="stylesheet" type="text/css" href="d3.parcoords.css">
<link rel="stylesheet" type="text/css" href="style.css">
<style>
/* data table styles */
#grid { height: 198px; }
.row, .header { clear: left; font-size: 12px; line-height: 18px; height: 18px; }
.row:nth-child(odd) { background: rgba(0,0,0,0.05); }
.header { font-weight: bold; }
.cell { float: left; overflow: hidden; white-space: nowrap; width: 100px; height: 18px; }
.col-0 { width: 180px; }
</style>
<script src="d3.min.js"></script>
<script src="d3.parcoords.js"></script>
<script src="divgrid.js"></script>
<h1>Math Assessments</h1>
<div id="chart1" class="parcoords" style="height:200px;"></div>
<br>
<div id="grid1"></div>

<script id="brushing">// quantitative color scale
var blue_to_brown = d3.scale.linear()
  .domain([9, 50])
  .range(["steelblue", "brown"])
  .interpolate(d3.interpolateLab);

var color = function(d) { return blue_to_brown(d['Distance']); };

var parcoords = d3.parcoords()("#chart1")
  .color(color)
  .alpha(0.4);

// load csv file and create the chart
d3.csv('math.csv', function(data) {
  parcoords
    .data(data)
    .render()
    .brushMode("1D-axes");  // enable brushing

  // create data table, row hover highlighting
  var grid = d3.divgrid();
  d3.select("#grid1")
    .datum(data.slice(0,10))
    .call(grid)
    .selectAll(".row")
    .on({
      "mouseover": function(d) { parcoords.highlight([d]) },
      "mouseout": parcoords.unhighlight
    });

  // update data table on brush event
  parcoords.on("brush", function(d) {
    d3.select("#grid1")
      .datum(d.slice(0,10))
      .call(grid)
      .selectAll(".row")
      .on({
        "mouseover": function(d) { parcoords.highlight([d]) },
        "mouseout": parcoords.unhighlight
      });
  });
});
</script>