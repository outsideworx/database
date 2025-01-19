import * as soupkitchen from './soupkitchen';

const moduleMap = {
    soupkitchen: soupkitchen.render
};

function lastPathSegment(request) {
    const url = new URL(request.url);
    const pathSegments = url.pathname.split('/').filter(Boolean);
    return pathSegments[pathSegments.length - 1];
}

export default {
    async fetch(request, env) {
        const {DATABASE} = env;
        const pathSegment = lastPathSegment(request);
        const { method } = request;

        if (method === "POST") {
            console.log('POST method is not allowed.');
            return new Response('Method not allowed.', {status: 405});
        }
        if (!(pathSegment in moduleMap)) {
            console.log('Path segment is not in the module map.');
            return new Response('Invalid input.', {status: 404});
        }

        const statement = DATABASE.prepare(`SELECT *
                                            FROM ${pathSegment}
                                            WHERE timestamp = (SELECT MAX (timestamp) FROM ${pathSegment})`);
        const {results} = await statement.all();
        return new Response(moduleMap[pathSegment](results), {headers: {'content-type': 'text/html'}});
    }
}
