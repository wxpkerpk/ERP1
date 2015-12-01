/**
 *
 */
function convert(rows) {
    function exists(rows, _parentId) {
        for (var i = 0; i < rows.length; i++) {
            if (rows[i].id == _parentId) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row._parentId)) {
            nodes.push({
                id: row.id,
                text: row.name,
                state:row.state
            });
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift();	// the parent node
        // get the children nodes
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row._parentId == node.id) {
                var child = {id: row.id, text: row.name,state:row.state};
                if (node.children) {
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}