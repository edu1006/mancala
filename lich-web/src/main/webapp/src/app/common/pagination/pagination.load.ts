import { FilterMetadata } from './filter.meta';
import { SortMeta } from './sort.meta';

export class PaginationLoadLazy {

    first?: number;
    rows?: number;
    sortField?: string;
    sortOrder?: number;
    multiSortMeta?: SortMeta[];
    filters?: {[s: string]: FilterMetadata; };
    globalFilter?: any;

}
