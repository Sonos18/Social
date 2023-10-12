import { useEffect, useState } from "react";
import NavPage from "./NavPage";
import { authApi, endpoints } from "../configs/APIS";
const ReportOfPost = () => {
  const [reports, setReports] = useState([]);
  const [totalPage, setTotalPage] = useState(null);
  const [page, setPage] = useState(0);
  useEffect(() => {
    const process = async () => {
      try {
        let { data } = await authApi().get(endpoints[`reports`], {
          params: {
            page: page,
            isPost: true,
          },
        });
        setReports(data.reportDto);
        setTotalPage(data.totalPage);
      } catch (error) {
        console.info(error);
      }
    };
    process();
  });
  return (
    <div className="w-full container max-w-3xl px-4 mx-auto sm:px-8">
      <div className="py-8">
        <div className="px-4 py-4 overflow-x-auto sm:-mx-8 sm:px-8">
          <div className="inline-block min-w-full overflow-hidden rounded-lg shadow">
            <table className="min-w-full leading-normal">
              <thead>
                <tr>
                  <th
                    scope="col"
                    className="px-8 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    User
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    Reason
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    Created at
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    postId
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  ></th>
                </tr>
              </thead>
              <tbody>
                {reports &&
                  reports.map((report) => {
                    return (
                      <tr>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <div className="flex items-center">
                            <div className="flex-shrink-0">
                              <div href="#" className="relative block">
                                <img
                                  alt="profil"
                                  src={report.userId.avatar}
                                  className="mx-auto object-cover rounded-full h-10 w-10 "
                                />
                              </div>
                            </div>
                            <div className="ml-3">
                              <p className="text-gray-900 whitespace-no-wrap">
                                {report.userId.username}
                              </p>
                            </div>
                          </div>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <p className="text-gray-900 whitespace-no-wrap">
                            {report.reason}
                          </p>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <p className="text-gray-900 whitespace-no-wrap">
                            {report.reportDate}
                          </p>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                        <p className="text-gray-900 whitespace-no-wrap">
                            {report.postId}
                          </p>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <button class="flex justify-end w-24 text-right">
                            <svg
                              width="20"
                              fill="currentColor"
                              height="20"
                              class="text-gray-500 hover:text-gray-800 dark:hover:text-white dark:text-gray-200"
                              viewBox="0 0 1792 1792"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <path d="M1363 877l-742 742q-19 19-45 19t-45-19l-166-166q-19-19-19-45t19-45l531-531-531-531q-19-19-19-45t19-45l166-166q19-19 45-19t45 19l742 742q19 19 19 45t-19 45z"></path>
                            </svg>
                          </button>
                        </td>
                      </tr>
                    );
                  })}
              </tbody>
            </table>
            {/* <NavPage totalPage={totalPage} onButtonClick={onButtonClick}/> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ReportOfPost;
