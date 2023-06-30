import { useCallback, useState } from "react";
import { GatewayDto } from "../../type/gateway-types";
import { deleteGateway } from "../../api/gateway-api";
import CreateGatewayForm from "./component/CreateGatewayForm";
import { ErrorDto } from "../../type/general-types";

export interface GatewaysProps {
  gateways: GatewayDto[];
}

const Gateways: React.FC<GatewaysProps> = (props) => {
  // state
  const [showCreateForm, setShowCreateForm] = useState<Boolean>(false);
  const [errorDto, setErrorDto] = useState<ErrorDto | null>(null);
  // handlers
  const addGatewayHandler = useCallback(
    () => setShowCreateForm((prev) => !prev),
    []
  );
  const removeGatewayHandler = useCallback(
    (gatewayId: number) => async () => {
      await deleteGateway(gatewayId);
      location.reload();
    },
    []
  );
  const closeCreateFormHandler = useCallback(() => {
    setShowCreateForm(false);
  }, []);
  const errorCallback = useCallback((body: ErrorDto) => {
    setErrorDto(body);
    setShowCreateForm(false);
  }, []);

  return (
    <div className="global-wrapper">
      <h1 className="title">Gateways</h1>
      {errorDto && <div className="error">{errorDto.message}</div>}
      <button onClick={addGatewayHandler}>Add gateway</button>
      {props.gateways.map((g, i) => (
        <div className="row v-normal-spaced">
          <a className="" href={`/gateways/${g.id}`}>
            {g.name}
          </a>
          <button
            className="h-normal-spaced"
            onClick={removeGatewayHandler(g.id)}
          >
            Remove
          </button>
        </div>
      ))}
      {showCreateForm && (
        <CreateGatewayForm responseCallback={errorCallback} formBackgroundClick={closeCreateFormHandler} />
      )}
    </div>
  );
};

export default Gateways;
