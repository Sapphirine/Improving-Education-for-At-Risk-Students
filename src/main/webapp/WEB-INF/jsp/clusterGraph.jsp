<style>
.node {
  stroke: #fff;
  stroke-width: 2.5px;
}

.link {
  stroke: #999;
  stroke-opacity: .6;
  stroke-width: 2.5px;
}
</style>
<body>
<script src="d3.v2.min.js"></script>
<script>

var width = 960,
    height = 300;

var color = d3.scale.category20();

var force = d3.layout.force()
    .charge(-120)
    .linkDistance(function(d){ return d.value + 20; })
    .charge(-1000)
    .size([width, height]);

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

d3.json("clusters.json", function(json) {
  var graph = json;
  console.log(graph);
    force
      .nodes(graph.nodes)
      .links(graph.links)
      .start();

  var link = svg.selectAll(".link")
      .data(graph.links)
    .enter().append("line")
      .attr("class", "link");

  var gnodes = svg.selectAll('g.gnode')
     .data(graph.nodes)
     .enter()
     .append('g')
     .classed('gnode', true);
    
  var node = gnodes.append("circle")
      .attr("class", "node")
      .attr("r", function(d) { return d.size; })
      .style("fill", function(d) { return color(d.group); })
      .call(force.drag);

  var labels = gnodes.append("text")
      .text(function(d) { return d.name; });

  console.log(labels);
    
  force.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; });

    gnodes.attr("transform", function(d) { 
        return 'translate(' + [d.x, d.y] + ')'; 
    });
  });
});

</script>